package com.founder.interservice.service;

import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.ResultObj;
import com.founder.interservice.model.Track;

import java.util.List;
import java.util.Map;

/**
 * 手机轨迹Service
 */
public interface IphoneTrackService {
    public Map<String,Object> iphoneTrackForSjhm(String obj, String kssj, String jssj) throws InterServiceException;
    public JSONObject getObjectRelation(String obj) throws InterServiceException;
    public ResultObj getObjectRelationAll(String obj, String type) throws InterServiceException;

    public JSONObject getObjectRelationAll(String obj)throws InterServiceException;
    public JSONObject getObjectRelatioNoSave(String obj) throws InterServiceException;
}
