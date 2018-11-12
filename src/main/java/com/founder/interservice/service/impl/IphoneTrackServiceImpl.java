package com.founder.interservice.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.TrackVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.*;
import com.founder.interservice.querymodel.RelationLocalFilter;
import com.founder.interservice.repository.RelationRepository;
import com.founder.interservice.repository.TrackRepository;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.HttpUtil;
import com.founder.interservice.util.KeyUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.founder.interservice.mapper.xzxt.ObjectRelationMapper;

import java.util.*;

/**
 * 手机轨迹service类
 */
@Service
public class IphoneTrackServiceImpl implements IphoneTrackService {

    @Value("${wabigdata.objectrelation.url}")
    private String objectrelation_url;
    @Value("${wabigdata.gettrack.url}")
    private String gettrack_url;
    @Value("${wabigdata.relationGetAll.url}")
    private String relationGetAll_url;
    /* JAP 轨迹 */
    @Autowired
    private TrackRepository trackRepository;
    /* JPA 关系 */
    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private ObjectRelationMapper objectRelationMapper;

    @Override
    public JSONObject getObjectRelationAll(String obj)throws InterServiceException{
        JSONObject jsonObject = null;
        try{
            String url = relationGetAll_url + "&objectValue="+obj;
            String result = HttpUtil.doGet(url);
            if(!StringUtil.ckeckEmpty(result)){
                if(result.startsWith("{") && result.endsWith("}")){
                    jsonObject = JSONObject.parseObject(result);
                }
            }
            return jsonObject;
        }catch (InterServiceException e){
            throw new InterServiceException(ResultEnum.REQUEST_URL_ERROR);
        }
    }

    /**
     *
     * @Description:
     * @Param:
     * @param obj 参数值 可为身份证 手机号码等...
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: cao peng
     * @date: 2018/8/15 0015-10:11
     */
    public ResultObj getObjectRelationAll(String obj,String type) throws InterServiceException{
        ResultObj resultObj = null;
        JSONObject jsonObejct = null;
        List<String> cphmTypes = Arrays.asList("6424","6422","6423","7888"); //汽车蓝色号码、汽车黄色号码、汽车白色号码，摩托车黄色号码
        List<String> sjhmTypes = Arrays.asList("20","4394","3996"); //手机号码类别
        String url = relationGetAll_url + "&objectValue="+obj;
        //String result = HttpUtil.doGet(url);
        //String result = "{\"children\":[{\"children\":[],\"objType\":437,\"objTypeName\":\"号牌号码\",\"objValue\":\"渝AZN748\"},{\"children\":[],\"objType\":4394,\"objTypeName\":\"电话号码\",\"objValue\":\"02379226951\"},{\"children\":[{\"children\":[{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"866091037961143\"},{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"861106038308428\"},{\"children\":[],\"objType\":4615,\"objTypeName\":\"微信ID\",\"objValue\":\"612087581\"}],\"objType\":4314,\"objTypeName\":\"IMSI\",\"objValue\":\"460110496721423\"}],\"objType\":3996,\"objTypeName\":\"手机号码\",\"objValue\":\"13308271988\"},{\"children\":[],\"objType\":4394,\"objTypeName\":\"电话号码\",\"objValue\":\"18008365980\"},{\"children\":[{\"children\":[{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"866091037961143\"},{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"861106038308428\"},{\"children\":[],\"objType\":4615,\"objTypeName\":\"微信ID\",\"objValue\":\"612087581\"}],\"objType\":4314,\"objTypeName\":\"IMSI\",\"objValue\":\"460110496721423\"}],\"objType\":4394,\"objTypeName\":\"电话号码\",\"objValue\":\"13308271988\"},{\"children\":[{\"children\":[{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"866091037961143\"},{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"861106038308428\"},{\"children\":[],\"objType\":4615,\"objTypeName\":\"微信ID\",\"objValue\":\"612087581\"}],\"objType\":4314,\"objTypeName\":\"IMSI\",\"objValue\":\"460110496721423\"}],\"objType\":20,\"objTypeName\":\"联系方式\",\"objValue\":\"13308271988\"},{\"children\":[],\"objType\":6424,\"objTypeName\":\"汽车蓝色号牌\",\"objValue\":\"渝AZN748\"},{\"children\":[],\"objType\":4394,\"objTypeName\":\"电话号码\",\"objValue\":\"02367625393\"},{\"children\":[{\"children\":[{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"866091038053692\"}],\"objType\":4314,\"objTypeName\":\"IMSI\",\"objValue\":\"460005331386186\"}],\"objType\":4394,\"objTypeName\":\"电话号码\",\"objValue\":\"13638201377\"}],\"objType\":1,\"objTypeName\":\"身份证号码\",\"objValue\":\"513523196904108017\"}";
        String result = "{\"children\":[{\"children\":[{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"866091037961143\"},{\"children\":[],\"objType\":4315,\"objTypeName\":\"IMEI\",\"objValue\":\"861106038308428\"},{\"children\":[],\"objType\":4615,\"objTypeName\":\"微信ID\",\"objValue\":\"612087581\"}],\"objType\":4314,\"objTypeName\":\"IMSI\",\"objValue\":\"460110496721423\"}],\"objType\":3996,\"objTypeName\":\"手机号码\",\"objValue\":\"13308271988\"}";
        if(!StringUtil.ckeckEmpty(result)){
            if(result.startsWith("{") && result.endsWith("}")){
                jsonObejct = JSONObject.parseObject(result);
            }
        }
        if(jsonObejct != null){
            resultObj = new ResultObj();
            String objType= jsonObejct.getString("objType");
            if(!"1".equals(objType)){
                if(sjhmTypes.contains(objType)){ //返回结果是手机号码及以下的关联
                    JSONObject jso = getSjhmsList(jsonObejct);
                    resultObj.setObjType(jso.getString("objType"));
                    resultObj.setObjTypeName(jso.getString("objTypeName"));
                    resultObj.setObjValue(jso.getString("objValue"));
                    resultObj.setQqhms(jso.getJSONArray("qqhms"));
                    resultObj.setWxhms(jso.getJSONArray("wxhms"));
                }
            }else{
                resultObj.setObjType(jsonObejct.getString("objType"));
                resultObj.setObjTypeName(jsonObejct.getString("objTypeName"));
                resultObj.setObjValue(jsonObejct.getString("objValue"));
                JSONArray childrens = jsonObejct.getJSONArray("children");
                if(childrens != null && childrens.size() > 0){
                    List<JSONObject> cphms = new ArrayList<JSONObject>();
                    List<JSONObject> sjhms = new ArrayList<JSONObject>();
                    for (int i = 0; i < childrens.size();i++){
                        JSONObject object = null;
                        JSONObject children = childrens.getJSONObject(i);
                        //车牌号
                        if(cphmTypes.contains(children.getString("objType"))){
                            object = new JSONObject();
                            object.put("objType","437");
                            object.put("objTypeName","号牌号码");
                            object.put("objValue",children.getString("objValue"));
                            if(cphms.isEmpty()){
                                cphms.add(object);
                            }else{
                                boolean b = false;
                                for (JSONObject o:cphms){
                                    if(o.getString("objValue").equals(children.getString("objValue"))){
                                        b = true;
                                        break;
                                    }else{
                                        continue;
                                    }
                                }
                                if(!b){cphms.add(object);}
                            }
                        } else if (sjhmTypes.contains(children.getString("objType"))){ //手机号码
                            object = getSjhmsList(children);
                            if(sjhms.isEmpty()){
                                sjhms.add(object);
                            }else{
                                boolean b = false;
                                for (JSONObject o:sjhms){
                                    if(o.getString("objValue").equals(children.getString("objValue"))){
                                        b = true;
                                        break;
                                    }else{
                                        continue;
                                    }
                                }
                                if(!b){sjhms.add(object);}
                            }
                        }
                    }
                    resultObj.setCphms(cphms);
                    resultObj.setSjhms(sjhms);
                }
            }
        }
        return resultObj;
    }

    /**
     * 提取手机号码的关联信息
     * @return
     */
    private JSONObject getSjhmsList(JSONObject children){
        String sjhm = children.getString("objValue");
        JSONObject object = null;
        if(sjhm != null && sjhm.length() == 11){
            object = new JSONObject();
            object.put("objType","4394");
            object.put("objTypeName","电话号码");
            object.put("objValue",children.getString("objValue"));
            JSONArray array = children.getJSONArray("children");
            List<JSONObject> wxhms = new ArrayList<JSONObject>();
            List<JSONObject> qqhms = new ArrayList<JSONObject>();
            if(array != null && !array.isEmpty()){
                JSONObject o = array.getJSONObject(0);
                if(o != null){
                    JSONArray a = o.getJSONArray("children");
                    if(a != null && !a.isEmpty()){
                        for (int j = 0;j < a.size(); j++){
                            JSONObject o1 = a.getJSONObject(j);
                            JSONObject o2 = new JSONObject();
                            o2.put("objType", o1.getString("objType"));
                            o2.put("objTypeName", o1.getString("objTypeName"));
                            o2.put("objValue", o1.getString("objValue"));
                            if(o1.getString("objType").equals("4615")){ //梳理添加微信号码
                                if(wxhms.isEmpty()){
                                    wxhms.add(o2);
                                }else{
                                    boolean b = false;
                                    for (JSONObject o3:wxhms){
                                        if(o3.getString("objValue").equals(o2.getString("objValue"))){
                                            b = true;
                                            break;
                                        }else{
                                            continue;
                                        }
                                    }
                                    if(!b){wxhms.add(o2);}
                                }
                            }else if(o1.getString("objType").equals("558")){ //梳理添加QQ号码
                                if(qqhms.isEmpty()){
                                    qqhms.add(o2);
                                }else{
                                    boolean b = false;
                                    for (JSONObject o3:qqhms){
                                        if(o3.getString("objValue").equals(o2.getString("objValue"))){
                                            b = true;
                                            break;
                                        }else{
                                            continue;
                                        }
                                    }
                                    if(!b){qqhms.add(o2);}
                                }
                            }
                        }
                    }
                }
            }
            object.put("wxhms",wxhms);
            object.put("qqhms",qqhms);

        }
        return object;
    }

    /**
     * 通过手机号码调取手机轨迹信息
     * @param obj 对象了类型：可为手机号、身份证号、imsi等
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    @Override
    public Map<String,Object> iphoneTrackForSjhm(String obj,String kssj,String jssj) throws InterServiceException {
        Map<String,Object> resultMap = null;
        JSONObject jsonObejct = null;
        String url = gettrack_url + "&objectValue="+obj+"&startTime="+kssj+"&endTime="+jssj;
        String result = HttpUtil.doGet(url);
        //String result = "{\"results\":[{\"address\":\"中国重庆北碚保亿紫园无线网络资源点\",\"base\":\"null\",\"j\":106.46750041645927,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"FMrkzI8rgzeciALkGIwi6Q==\",\"timestamp\":1533276986000,\"w\":29.72508301582967},{\"address\":\" \",\"base\":\"null\",\"j\":106.46750041645927,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"2dcoWfhPoRRw5GzanGHj0A==\",\"timestamp\":1533276936000,\"w\":29.72508301582967},{\"address\":\"中国重庆合川三汇兴旺街资源点\",\"base\":\"460003333db65682\",\"j\":106.5964763499098,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"nj7A/fyzWq57tTBYdKIsjw==\",\"timestamp\":1533104872000,\"w\":30.0796412256528}],\"status\":\"ok\"}";
        if(!StringUtil.ckeckEmpty(result)){
            while(!result.startsWith("{")){
                result = HttpUtil.doGet(url);
            }
            jsonObejct = JSONObject.parseObject(result);
        }
        if(jsonObejct != null){
            JSONArray jsonArray = jsonObejct.getJSONArray("results");
            if(jsonArray != null && jsonArray.size() > 0){
                resultMap = new HashMap<String,Object>();
                List<Track> tracks = jsonArray.toJavaList(Track.class);
                List<TrackVO> trackVOs = new ArrayList<TrackVO>();
                if(tracks != null && tracks.size() > 0){
                    List<Track> paramTracks = new ArrayList<Track>();
                    for (Track track : tracks ) {
                        if(!StringUtils.isEmpty(track.getTimestamp())){
                            track.setTimestr(DateUtil.getDateTime(new Date(Long.valueOf(track.getTimestamp()))));
                        }

                        TrackVO trackVO = new TrackVO();
                        BeanUtils.copyProperties(track,trackVO);
                        trackVOs.add(trackVO);

                        Track pTrack = new Track();
                        pTrack.setObjectvalue(track.getObjectvalue());
                        pTrack.setTimestamp(track.getTimestamp());
                        Example<Track> example = Example.of(pTrack);

                        if(paramTracks.isEmpty()){
                            boolean bol = trackRepository.exists(example);
                            if (!bol) {
                                track.setXxzjbh(KeyUtil.getUniqueKey("T"));
                                paramTracks.add(track);
                            }
                        }else{
                            boolean b = false;
                            for (Track t: paramTracks) {
                                if(t.getObjectvalue().equals(track.getObjectvalue()) &&
                                        t.getTimestamp().equals(track.getTimestamp())){
                                    b = true;
                                    break;
                                }else{
                                    continue;
                                }
                            }
                            if(!b){
                                boolean bol = trackRepository.exists(example);
                                if (!bol) {
                                    track.setXxzjbh(KeyUtil.getUniqueKey("T"));
                                    paramTracks.add(track);
                                }
                            }
                        }
                    }
                    if (paramTracks != null && paramTracks.size() > 0){
                        trackRepository.save(paramTracks);
                    }
                    resultMap.put("objectfromtype","IMSI");
                    resultMap.put("objectfromvalue",obj);
                    resultMap.put("data",trackVOs);
                }else{
                    resultMap.put("objectfromtype","IMSI");
                    resultMap.put("objectfromvalue",obj);
                    resultMap.put("data",trackVOs);
                }
            }
        }
        return resultMap;
    }

    @Override
    public JSONObject getObjectRelatioNoSave(String obj) throws InterServiceException{
        try{
            JSONObject jsonObejct = null;
            String url = objectrelation_url+"&objectValue="+obj;
            String result = HttpUtil.doGet(url);
            if(!StringUtil.ckeckEmpty(result)){
                if(result.startsWith("{") && result.endsWith("}")){
                    jsonObejct = JSONObject.parseObject(result);
                }
            }
            return jsonObejct;
        }catch (Exception e){
            throw new InterServiceException(ResultEnum.REQUEST_URL_ERROR);
        }
    }


    /**
     * 通过某一对象调取与之有关系的另一对象
     * @param obj 参数：可是手机号、身份证号、护照号等等...
     * @return
     */
    @Override
    public JSONObject getObjectRelation(String obj) throws InterServiceException {
        JSONObject jsonObejct = null;
        String url = objectrelation_url+"&objectValue="+obj;
        String result = HttpUtil.doGet(url);
        //String result = "{\"results\":[{\"ccv \":227,\"first_timestamp\":1531908084000,\"objectFromType\":4394,\"objectFromTypeName\":\"电话号码\",\"objectFromValue\":\"15129457465\",\"objectToType\":4314,\"objectToTypeName\":\"IMSI\",\"objectToValue\":\"460001180780041\",\"relativeType\":4402,\"relativeTypeName\":\"手机-IMSI\",\"source\":1094,\"sourceName\":\"4G-微信\",\"source_md5\":\"cfnQ37fBveUMP7kTZJqoDA==\",\"timestamp\":1533743041000},{\"count\":726,\"first_timestamp\":1531908024000,\"objectFromType\":4394,\"objectFromTypeName\":\"电话号码\",\"objectFromValue\":\"15129457465\",\"objectToType\":4314,\"objectToTypeName\":\"IMSI\",\"objectToValue\":\"460001180780041\",\"relativeType\":4402,\"relativeTypeName\":\"手机-IMSI\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"lgnna+LmDFyEJ+BTVmtmQg==\",\"timestamp\":1533745282000}],\"status\":\"ok\"}";
        if(!StringUtil.ckeckEmpty(result)){
            while(!result.startsWith("{")){
                result = HttpUtil.doGet(url);
            }
            jsonObejct = JSONObject.parseObject(result);
        }
        if(jsonObejct != null){
            JSONArray jsonArray = jsonObejct.getJSONArray("results");
            if(jsonArray != null && jsonArray.size() > 0){
                List<Relation> relations = jsonArray.toJavaList(Relation.class);
                if(relations != null && !relations.isEmpty()){
                    List<Relation> paramRelations = new ArrayList<Relation>();
                    for (Relation r : relations) {
                        if(!StringUtils.isEmpty(r.getFirst_timestamp())){
                            r.setFirst_timestamp(DateUtil.getDateTime(new Date(Long.valueOf(r.getFirst_timestamp()))));
                        }
                        if(!StringUtils.isEmpty(r.getTimestamp())){
                            r.setTimestamp(DateUtil.getDateTime(new Date(Long.valueOf(r.getTimestamp()))));
                        }

                        Relation relation = new Relation();
                        relation.setObjectfromvalue(r.getObjectfromvalue());
                        relation.setObjecttovalue(r.getObjecttovalue());
                        Example<Relation> example = Example.of(relation);

                        if(paramRelations.isEmpty()){
                            boolean bol = relationRepository.exists(example);
                            if(!bol){
                                r.setXxzjbh(KeyUtil.getUniqueKey("R"));
                                paramRelations.add(r);
                            }
                        }else{
                            boolean b = false;
                            for ( Relation pRelation: paramRelations) {
                                if(pRelation.getObjectfromvalue().equals(r.getObjectfromvalue())
                                        && pRelation.getObjecttovalue().equals(r.getObjecttovalue())){
                                    b = true;
                                    break;
                                }else{
                                    continue;
                                }
                            }
                            if(!b){
                                boolean bol = relationRepository.exists(example);
                                if(!bol){
                                    r.setXxzjbh(KeyUtil.getUniqueKey("R"));
                                    paramRelations.add(r);
                                }
                            }
                        }
                    }
                    if(paramRelations != null && paramRelations.size() > 0){
                        relationRepository.save(paramRelations);
                    }
                }
            }
        }
        return jsonObejct;
    }

    /**
     * 保存关联数据
     * @param resultObj 结果集
     * @param asjbh 案事件编号
     * @param sfzh 身份证号
     * @param jh 警号
     */
    @Override
    public void saveObjectRelationLocal(ResultObj resultObj,String asjbh,String sfzh,String jh) {
        String objType = resultObj.getObjType();
        if("1".equals(objType)){
            String idcard = resultObj.getObjValue();
            List<Map<String,Object>> cphms = resultObj.getCphms();
            List<Map<String,Object>> sjhms = resultObj.getSjhms();
            if (null != cphms || cphms.size() !=0){
                for (Map<String,Object> cphm:cphms){
                    IdcardPhoneCarRalation idRelation = new IdcardPhoneCarRalation();
                    String car = cphm.get("objValue").toString();
                    String xxzjbh = UUID.randomUUID().toString().replaceAll("-","");
                    xxzjbh = xxzjbh.substring(0,xxzjbh.length()-2);
                    idRelation.setXxzjbh(xxzjbh);
                    idRelation.setAsjbh(asjbh);
                    idRelation.setSfzh(sfzh);
                    idRelation.setJh(jh);
                    idRelation.setIdcard(idcard);
                    idRelation.setCar(car);
                    idRelation.setPhone("");
                    try {
                        objectRelationMapper.saveIdRelation(idRelation);//--保存身份证号和车牌号--
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            if(null != sjhms || sjhms.size() !=0){
                for (Map<String,Object> sjhm:sjhms){
                    IdcardPhoneCarRalation idRelation = new IdcardPhoneCarRalation();
                    //主表
                    String phone = sjhm.get("objValue").toString();
                    String xxzjbh = UUID.randomUUID().toString().replaceAll("-","");
                    xxzjbh = xxzjbh.substring(0,xxzjbh.length()-2);
                    idRelation.setXxzjbh(xxzjbh);
                    idRelation.setAsjbh(asjbh);
                    idRelation.setSfzh(sfzh);
                    idRelation.setJh(jh);
                    idRelation.setIdcard(idcard);
                    idRelation.setPhone(phone);
                    idRelation.setCar("");
                    try {
                        objectRelationMapper.saveIdRelation(idRelation);//--保存身份证号和电话号码--
                        this.savePhoneRelation(sjhm,asjbh,sfzh,jh);//--保存电话号码和QQ微信--
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }else if("4394".equals(objType)){
            Map<String,Object> sjhm = new HashMap<String,Object>();
            sjhm.put("objValue",resultObj.getObjValue());
            sjhm.put("objType", resultObj.getObjType());
            sjhm.put("objTypeName", resultObj.getObjTypeName());
            sjhm.put("qqhms",resultObj.getQqhms());
            sjhm.put("wxhms", resultObj.getWxhms());
            this.savePhoneRelation(sjhm,asjbh,sfzh,jh);//--保存电话号码和QQ微信--

        }
    }

    //--保存电话号码和QQ微信--
    public void savePhoneRelation(Map<String,Object> sjhm,String asjbh,String sfzh,String jh){
        String phone = sjhm.get("objValue").toString();
        List<Map<String,Object>> qqhms = (List<Map<String,Object>>)sjhm.get("qqhms");
        List<Map<String,Object>> wxhms = (List<Map<String,Object>>)sjhm.get("wxhms");
        if (null != qqhms || qqhms.size() !=0){
            for (Map<String,Object> qqhm:qqhms){
                PhoneQQWeChatRalation phoneRelation = new PhoneQQWeChatRalation();
                String qq = qqhm.get("objValue").toString();
                String xxzjbh = UUID.randomUUID().toString().replaceAll("-","");
                xxzjbh = xxzjbh.substring(0,xxzjbh.length()-2);
                phoneRelation.setXxzjbh(xxzjbh);
                phoneRelation.setAsjbh(asjbh);
                phoneRelation.setSfzh(sfzh);
                phoneRelation.setJh(jh);
                phoneRelation.setPhone(phone);
                phoneRelation.setQq(qq);
                phoneRelation.setWechat("");
                try {
                    objectRelationMapper.savePhoenRelation(phoneRelation);//--保存手机号与QQ关系--
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        if (null != wxhms || wxhms.size() !=0){
            for (Map<String,Object> wxhm:wxhms){
                PhoneQQWeChatRalation phoneRelation = new PhoneQQWeChatRalation();
                String wechat = wxhm.get("objValue").toString();
                String xxzjbh = UUID.randomUUID().toString().replaceAll("-","");
                xxzjbh = xxzjbh.substring(0,xxzjbh.length()-2);
                phoneRelation.setXxzjbh(xxzjbh);
                phoneRelation.setAsjbh(asjbh);
                phoneRelation.setSfzh(sfzh);
                phoneRelation.setJh(jh);
                phoneRelation.setPhone(phone);
                phoneRelation.setWechat(wechat);
                phoneRelation.setQq("");
                try {
                    objectRelationMapper.savePhoenRelation(phoneRelation);//--保存手机号与微信关系--
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean queryObjectRelationLocal(RelationLocalFilter filter) {
        boolean yesOrNo = false;
        String phone = "";
        String idcard = "";
        try{
            if(!StringUtil.ckeckEmpty(filter.getIdcard())||!StringUtil.ckeckEmpty(filter.getPhone())||!StringUtil.ckeckEmpty(filter.getCar())){
                if(StringUtil.ckeckEmpty(filter.getQq())&&StringUtil.ckeckEmpty(filter.getWechat())){
                    //保证参数只有idcard,phone,car
                    List<String> idcards = new ArrayList<String>();
                    idcards = objectRelationMapper.queryIdRelationLocal(filter);
                    if (idcards!=null&&idcards.size()!=0){
                        yesOrNo = true;
                    }else{
                        List<String> phones = objectRelationMapper.queryPhoneRelationLocal(filter);
                        if(null != phones && !phones.isEmpty()){
                            yesOrNo = true;
                        }
                    }
                }
            }else if(!StringUtil.ckeckEmpty(filter.getQq())||!StringUtil.ckeckEmpty(filter.getWechat())){
                if(StringUtil.ckeckEmpty(filter.getIdcard())&&StringUtil.ckeckEmpty(filter.getPhone())&&StringUtil.ckeckEmpty(filter.getCar())){
                    //保证参数只有QQ微信
                    List<String> phones = new ArrayList<String>();
                    phones = objectRelationMapper.queryPhoneRelationLocal(filter);
                    if (phones!=null&&phones.size()!=0){
                        phone = phones.get(0);
                    }
                    RelationLocalFilter filterNew = new RelationLocalFilter();
                    filterNew.setPhone(phone);
                    List<String> idcards = new ArrayList<String>();
                    idcards = objectRelationMapper.queryIdRelationLocal(filter);
                    if (idcards!=null&&idcards.size()!=0){
                        yesOrNo = true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return yesOrNo;
    }
}


