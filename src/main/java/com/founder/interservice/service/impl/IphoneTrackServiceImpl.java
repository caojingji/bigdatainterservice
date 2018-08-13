package com.founder.interservice.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.TrackVO;
import com.founder.interservice.model.Relation;
import com.founder.interservice.model.Track;
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
    /* JAP 轨迹 */
    @Autowired
    private TrackRepository trackRepository;
    /* JPA 关系 */
    @Autowired
    private RelationRepository relationRepository;


    /**
     * 通过手机号码调取手机轨迹信息
     * @param obj 对象了类型：可为手机号、身份证号、imsi等
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    @Override
    public Map<String,Object> iphoneTrackForSjhm(String obj,String kssj,String jssj) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String url = gettrack_url + "&objectValue="+obj+"&startTime="+kssj+"&endTime="+jssj;
        //String result = HttpUtil.doGet(url);
        String result = "{\"results\":[{\"address\":\"中国重庆北碚保亿紫园无线网络资源点\",\"base\":\"null\",\"j\":106.46750041645927,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"FMrkzI8rgzeciALkGIwi6Q==\",\"timestamp\":1533276986000,\"w\":29.72508301582967},{\"address\":\" \",\"base\":\"null\",\"j\":106.46750041645927,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"2dcoWfhPoRRw5GzanGHj0A==\",\"timestamp\":1533276936000,\"w\":29.72508301582967},{\"address\":\"中国重庆合川三汇兴旺街资源点\",\"base\":\"460003333db65682\",\"j\":106.5964763499098,\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460001180780041\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"nj7A/fyzWq57tTBYdKIsjw==\",\"timestamp\":1533104872000,\"w\":30.0796412256528}],\"status\":\"ok\"}";
        JSONObject jsonObejct = JSONObject.parseObject(result);
        if(jsonObejct != null){
            JSONArray jsonArray = jsonObejct.getJSONArray("results");
            if(jsonArray != null && jsonArray.size() > 0){
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
                        //trackRepository.save(paramTracks);
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

    /**
     * 通过某一对象调取与之有关系的另一对象
     * @param obj 参数：可是手机号、身份证号、护照号等等...
     * @return
     */
    @Override
    public JSONObject getObjectRelation(String obj){
        String url = objectrelation_url+"&objectValue="+obj;
        //String result = HttpUtil.doGet(url);
        String result = "{\"results\":[{\"ccv \":227,\"first_timestamp\":1531908084000,\"objectFromType\":4394,\"objectFromTypeName\":\"电话号码\",\"objectFromValue\":\"15129457465\",\"objectToType\":4314,\"objectToTypeName\":\"IMSI\",\"objectToValue\":\"460001180780041\",\"relativeType\":4402,\"relativeTypeName\":\"手机-IMSI\",\"source\":1094,\"sourceName\":\"4G-微信\",\"source_md5\":\"cfnQ37fBveUMP7kTZJqoDA==\",\"timestamp\":1533743041000},{\"count\":726,\"first_timestamp\":1531908024000,\"objectFromType\":4394,\"objectFromTypeName\":\"电话号码\",\"objectFromValue\":\"15129457465\",\"objectToType\":4314,\"objectToTypeName\":\"IMSI\",\"objectToValue\":\"460001180780041\",\"relativeType\":4402,\"relativeTypeName\":\"手机-IMSI\",\"source\":1046,\"sourceName\":\"4G认证\",\"source_md5\":\"lgnna+LmDFyEJ+BTVmtmQg==\",\"timestamp\":1533745282000}],\"status\":\"ok\"}";
        JSONObject jsonObejct = JSONObject.parseObject(result);
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
                        //relationRepository.save(paramRelations);
                    }
                }
            }
        }
        return jsonObejct;
    }
}


