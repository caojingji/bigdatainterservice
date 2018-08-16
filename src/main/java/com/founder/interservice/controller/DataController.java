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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
@CrossOrigin
public class DataController {

    @Autowired
    private DataService dataService;
    @Autowired
    private IphoneTrackService iphoneTrackService;
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
    * @Description: 跳转到轨迹展示界面 同时调用网安接口查询轨迹信息 将数据入库保存
    * @Param:
     * @param objValue 参数值
     * @param kssj  开始时间
     * @param jssj  结束时间
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-10:26
    */
    @RequestMapping(value = "/toGjzs")
    public ModelAndView toGjzs(String objValue, String kssj, String jssj){
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
                    Map<String, Object> resultMap = iphoneTrackService.iphoneTrackForSjhm(imsi, kssjstr, jssjStr);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("objValue",objValue);
        modelAndView.addObject("kssj",kssj);
        modelAndView.addObject("jssj",jssj);
        modelAndView.setViewName("jzgjzs");
        return modelAndView;
    }

    @RequestMapping(value = "/queryTrackBefore5")
    @ResponseBody
    public List<TrackVO> queryTrackBefore5(String objValue, String kssj, String jssj){
        List<TrackVO> trackVOS = null;
        try {
            RelationFilter relationFilter = new RelationFilter();
            relationFilter.setObjectfromvalue(objValue);
            relationFilter.setObjecttotype("4314");
            List<Relation> relations = dataService.queryRelationsList(relationFilter);
            TrackFilter trackFilter = new TrackFilter();
            trackFilter.setKssj(kssj);
            trackFilter.setJssj(jssj);
            if(relations != null && !relations.isEmpty()){
                String imsiStr = "";
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
                trackFilter.setObjectvalue(imsiStr);
            }
            List<Track> tracks = dataService.queryTrackBefore5(trackFilter);
            if(tracks != null && !tracks.isEmpty()){
                trackVOS = new ArrayList<>();
                for (Track track: tracks) {
                    TrackVO trackVO = new TrackVO();
                    BeanUtils.copyProperties(track,trackVO);
                    trackVOS.add(trackVO);
                }
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
            RelationFilter relationFilter = new RelationFilter();
            relationFilter.setObjectfromvalue(objValue);
            relationFilter.setObjecttotype("4314");
            List<Relation> relations = dataService.queryRelationsList(relationFilter);
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
            if(relations != null && !relations.isEmpty()){
                String imsiStr = "";
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
                trackFilter.setObjectvalue(imsiStr);
            }
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return objectMap;
    }

}
