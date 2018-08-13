package com.founder.interservice.service;

import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.model.Track;

import java.util.List;
import java.util.Map;

/**
 * 手机轨迹Service
 */
public interface IphoneTrackService {
    public Map<String,Object> iphoneTrackForSjhm(String obj, String kssj, String jssj);
    public JSONObject getObjectRelation(String obj);
}
