package com.founder.interservice.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.TrackVO;
import com.founder.interservice.model.Relation;
import com.founder.interservice.model.Track;
import com.founder.interservice.querymodel.RelationFilter;
import com.founder.interservice.querymodel.TrackFilter;
import com.founder.interservice.service.DataService;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.EasyUIPage;
import com.founder.interservice.util.HttpClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName： DateController
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-15 13:43
 * @Version: 1.0
 */
@Controller
@CrossOrigin //跨域访问
public class DataController {

    @Autowired
    private DataService dataService;
    @Autowired
    private IphoneTrackService iphoneTrackService;
    @Value("${wabigdata.pgis.url}")
    private String PGIS_URL;
    @Value("${wabigdata.pgis_title.url}")
    private String PGIS_TITLE_URL;
    /**
    *
    * @Description:跳转到轨迹查询界面
    * @Param:
        * @param
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-10:26
    */
    @RequestMapping("/toGjcx")
    public String toGjcx(){
        return "jzgj";
    }


    /**
     *
     * @Description: 获取和保存网安轨迹数据
     * @Param:
     * @param objValue 手机号，QQ号，微信号
     * @param kssj 轨迹开始时间
     * @param jssj 轨迹结束时间
     * @return: void
     * @Author: cao peng
     * @date: 2018/9/19 0019-9:56
     */
    @RequestMapping(value = "/getAndSaveTrack")
    public void getAndSaveTrack(String objValue, String kssj, String jssj){
        try{
            List<String> imsis = new ArrayList<>();
            JSONObject jsonObject = iphoneTrackService.getObjectRelation(objValue);
            if(jsonObject != null){
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                if(jsonArray != null && jsonArray.size() > 0){
                    for (int i = 0; i <= jsonArray.size() - 1;i++){
                        JSONObject resObj = jsonArray.getJSONObject(i);
                        String objectType = resObj.getString("objectToTypeName");
                        if (objectType != null && "IMSI".equals(objectType)){
                            String imsi = (String) resObj.get("objectToValue");
                            if(!imsis.contains(imsi)){
                                imsis.add(imsi);
                            }
                        }
                    }
                }
            }
            if(imsis != null && !imsis.isEmpty()) {
                String kssjstr = kssj.contains(" ") ? DateUtil.convertStringToDateTime(kssj).getTime() + "" : DateUtil.convertStringToDate(kssj).getTime() + "";
                String jssjStr = jssj.contains(" ") ? DateUtil.convertStringToDateTime(jssj).getTime() + "" : DateUtil.convertStringToDate(jssj).getTime() + "";
                for (String imsi : imsis) {
                    iphoneTrackService.iphoneTrackForSjhm(imsi, kssjstr, jssjStr);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
    *
    * @Description: 跳转到轨迹展示界面 同时调用网安接口查询轨迹信息 将数据入库保存
    * @Param:
     * @param objValue 参数值
     * @param kssj  开始时间
     * @param jssj  结束时间
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-10:26
    */
    @RequestMapping(value = "/toGjzs",method = {RequestMethod.POST,RequestMethod.GET})
    public String toGjzs(String objValue, String kssj, String jssj, Model model){
        getAndSaveTrack(objValue, kssj, jssj);
        model.addAttribute("objValue", objValue);
        model.addAttribute("kssj", kssj);
        model.addAttribute("jssj", jssj);
        return "jzgjzs";
    }

    /**
    *
    * @Description:  跳转地图pgis页面
    * @Param:
        * @param
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-17:23
    */
    @RequestMapping(value = "/toGjzsPgis",method = {RequestMethod.GET,RequestMethod.POST})
    public void toGjzsPgis(HttpServletResponse httpServletResponse,
                           String objValue, String kssj, String jssj){
        String paramStr = null;
        try{
            getAndSaveTrack(objValue, kssj, jssj);
            /*数据入口后再进行查取操作*/
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            trackFilter.setObjectvalue(getImsiStr(objValue));
            List<Track> tracks = dataService.queryTracksListNoPage(trackFilter);
            if(tracks != null && !tracks.isEmpty()){
                if(tracks.size() == 1){
                    paramStr = tracks.get(0).getJ() + "," +tracks.get(0).getW();
                }else{
                    for (int i = 0;i < tracks.size(); i++){
                        if(i == 0){
                            paramStr = tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                        }else if(i == tracks.size() - 1 ){
                            paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW();
                        }else{
                            paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                        }
                    }
                }
            }
            //httpServletResponse.sendRedirect(PGIS_URL + "markers="+paramStr);
            //使用post发送请求
            HttpClient httpClient = new HttpClient(httpServletResponse);
            httpClient.setParameter("flag", "search");
            httpClient.setParameter("markers", paramStr);
            httpClient.sendByPost(PGIS_URL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    *
    * @Description: 最新位置跳转到地图展示
    * @Param:
    * @return:
    * @Author: cao peng
    * @date: 2018/9/17 0017-12:41
    */
    @RequestMapping(value = "/queryNewLocation_pgis")
    public void queryNewLocation_pgis(HttpServletResponse response,String objValue, String kssj, String jssj){
        try{
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryNewLocation(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    Track track = tracks.get(0);
                    response.sendRedirect(PGIS_TITLE_URL+"&jd="+track.getJ()+"&wd="+track.getW()+"&title="+track.getAddress());
                }else{
                    response.sendRedirect(PGIS_TITLE_URL+"&jd=&wd=&title=");
                }
            }else{
                response.sendRedirect(PGIS_TITLE_URL+"&jd=&wd=&title=");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     *
     * @Description: 查询指定时间段内最新位置
     * @Param:
     * @param objValue
     * @param kssj
     * @param jssj
     * @return: java.util.List<com.founder.interservice.VO.TrackVO>
     * @Author: cao peng
     * @date: 2018/9/13 0013-15:28
     */
    @RequestMapping(value = "/queryNewLocation",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<TrackVO> queryNewLocation(String objValue, String kssj, String jssj){
        List<TrackVO> trackVOS = null;
        try {
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryNewLocation(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    trackVOS = new ArrayList<>();
                    for (Track track: tracks) {
                        TrackVO trackVO = new TrackVO();
                        BeanUtils.copyProperties(track,trackVO);
                        trackVOS.add(trackVO);
                    }
                }
            }else{
                trackVOS = new ArrayList<>();
            }
        }catch (Exception e){
            e.printStackTrace();
            trackVOS = new ArrayList<>();
        }
        return trackVOS;
    }

    @RequestMapping(value = "/queryTracksByRSD_pgis")
    public void queryTracksByRSD_pgis(HttpServletResponse servletResponse,String objValue, String kssj, String jssj,String base){
        String paramStr = null;
        try {
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            if("01".equals(base)){
                trackFilter.setBase("夜间");
            }else if ("02".equals(base)) {
                trackFilter.setBase("上午");
            }else if("03".equals(base)){
                trackFilter.setBase("下午");
            }else if("04".equals(base)){
                trackFilter.setBase("傍晚");
            }
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryTracksByRSD(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    if(tracks.size() == 1){
                        paramStr = tracks.get(0).getJ() + "," +tracks.get(0).getW();
                    }else{
                        for (int i = 0;i < tracks.size(); i++){
                            if(i == 0){
                                paramStr = tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                            }else if(i == tracks.size() - 1 ){
                                paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW();
                            }else{
                                paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                            }
                        }
                    }
                }
                //httpServletResponse.sendRedirect(PGIS_URL + "markers="+paramStr);
                //使用post发送请求
                HttpClient httpClient = new HttpClient(servletResponse);
                httpClient.setParameter("markers",paramStr);
                httpClient.sendByPost(PGIS_URL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *
     * @Description: 根据日时段对查询每个时段 停留时间最多的五个点
     * @Param:
     * @param objValue
     * @param kssj
     * @param jssj
     * @param base
     * @return: java.util.List<com.founder.interservice.VO.TrackVO>
     * @Author: cao peng
     * @date: 2018/8/17 0017-14:18
     */
    @RequestMapping(value = "/queryTracksByRSD")
    @ResponseBody
    public List<TrackVO> queryTracksByRSD(String objValue, String kssj, String jssj,String base){
        List<TrackVO> trackVOS = null;
        try {
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            if("01".equals(base)){
                trackFilter.setBase("夜间");
            }else if ("02".equals(base)) {
                trackFilter.setBase("上午");
            }else if("03".equals(base)){
                trackFilter.setBase("下午");
            }else if("04".equals(base)){
                trackFilter.setBase("傍晚");
            }
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryTracksByRSD(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    trackVOS = new ArrayList<>();
                    for (Track track: tracks) {
                        TrackVO trackVO = new TrackVO();
                        BeanUtils.copyProperties(track,trackVO);
                        trackVOS.add(trackVO);
                    }
                }
            }else{
                trackVOS = new ArrayList<>();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return trackVOS;
    }
    /**
    * 
    * @Description: 关联次数最多的五次 使用地图进行展现
    * @Param:
    * @return:
    * @Author: cao peng
    * @date: 2018/9/17 0017-12:47
    */
    @RequestMapping(value = "/queryTrackBefore5_pgis")
    public void queryTrackBefore5_pgis(HttpServletResponse httpServletResponse,String objValue, String kssj, String jssj){
        String paramStr = null;
        try {
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryTrackBefore5(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    if(tracks.size() == 1){
                        paramStr = tracks.get(0).getJ() + "," +tracks.get(0).getW();
                    }else{
                        for (int i = 0;i < tracks.size(); i++){
                            if(i == 0){
                                paramStr = tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                            }else if(i == tracks.size() - 1 ){
                                paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW();
                            }else{
                                paramStr = paramStr + tracks.get(i).getJ() + "," +tracks.get(i).getW() + ";";
                            }
                        }
                    }
                }
                //httpServletResponse.sendRedirect(PGIS_URL + "markers="+paramStr);
                //使用post发送请求
                HttpClient httpClient = new HttpClient(httpServletResponse);
                httpClient.setParameter("markers",paramStr);
                httpClient.sendByPost(PGIS_URL);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
    * 
    * @Description:  查询指定时间段内抓取次数最多的五次轨迹
    * @Param:
        * @param
    * @return:
    * @Author: cao peng
    * @date: 2018/8/17 0017-10:36
    */
    @RequestMapping(value = "/queryTrackBefore5")
    @ResponseBody
    public List<TrackVO> queryTrackBefore5(String objValue, String kssj, String jssj){
        List<TrackVO> trackVOS = null;
        try {
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            String imsis = getImsiStr(objValue);
            if(imsis != null && !imsis.isEmpty()){
                trackFilter.setObjectvalue(imsis);
                List<Track> tracks = dataService.queryTrackBefore5(trackFilter);
                if(tracks != null && !tracks.isEmpty()){
                    trackVOS = new ArrayList<>();
                    for (Track track: tracks) {
                        TrackVO trackVO = new TrackVO();
                        BeanUtils.copyProperties(track,trackVO);
                        trackVOS.add(trackVO);
                    }
                }
            }else{
                trackVOS = new ArrayList<>();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return trackVOS;
    }

    /**
     *
     * @Description: 分页多条件查询手机轨迹
     * @Param:
     * @param objValue 参数值
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: cao peng
     * @date: 2018/8/16 0016-15:02
     */
    @RequestMapping("/queryTrackList")
    @ResponseBody
    public Map<String,Object> queryTrackList(String objValue, String kssj, String jssj,
                                             @RequestParam(value = "page",defaultValue = "0") int page,
                                             @RequestParam(value = "rows",defaultValue = "0") int rows){
        Map<String,Object> objectMap = new HashMap<>();
        List<TrackVO> trackVOS = null;
        try{
            EasyUIPage easyUIPage = new EasyUIPage();
            easyUIPage.setPage(page);
            easyUIPage.setPagePara(rows);
            int begin = easyUIPage.getBegin();
            int end = easyUIPage.getEnd();

            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            trackFilter.setBegin(begin);
            trackFilter.setEnd(end);
            String imsiStr = getImsiStr(objValue);
            if(imsiStr != null && !imsiStr.isEmpty()){
                trackFilter.setObjectvalue(imsiStr);
                Map<String,Object> resultMap = dataService.queryTracksList(trackFilter);
                int total = (int)resultMap.get("total");
                List<Track> tracks = (List<Track>)resultMap.get("tracks");
                if(tracks != null && !tracks.isEmpty()){
                    trackVOS = new ArrayList<>();
                    for (Track track: tracks) {
                        TrackVO trackVO = new TrackVO();
                        BeanUtils.copyProperties(track,trackVO);
                        trackVOS.add(trackVO);
                    }
                }
                objectMap.put("total", total);
                objectMap.put("rows", trackVOS);
            }else{
                objectMap.put("total", 0);
                objectMap.put("rows", new ArrayList<>());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return objectMap;
    }
    /**
     *
     * @Description: 根据条件获取relation（IMSI）
     * @Param:
     * @param objValue  手机号或者qq号
     * @return: java.util.List<com.founder.interservice.model.Relation>
     * @Author: cao peng
     * @date: 2018/8/16 0016-17:26
     */
    private String getImsiStr(String objValue){
        String imsiStr = "";
        try{
            RelationFilter relationFilter = new RelationFilter();
            relationFilter.setObjectfromvalue(objValue);
            relationFilter.setObjecttotype("4314");
            List<Relation> relations = dataService.queryRelationsList(relationFilter);
            if(relations != null && !relations.isEmpty()){
                if(relations.size() == 1){
                    imsiStr = relations.get(0).getObjecttovalue();
                }else{
                    for (int i = 0;i < relations.size(); i++) {
                        if(i == relations.size() - 1){
                            imsiStr = imsiStr + "'" + relations.get(i).getObjecttovalue() + "'";
                        }else if(i == 0){
                            imsiStr = "'" + relations.get(i).getObjecttovalue() + "',";
                        }else{
                            imsiStr = imsiStr + "'" + relations.get(i).getObjecttovalue() + "',";
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imsiStr;
    }

}
