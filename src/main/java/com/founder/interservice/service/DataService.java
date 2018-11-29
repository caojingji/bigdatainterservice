package com.founder.interservice.service;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.Relation;
import com.founder.interservice.model.Track;
import com.founder.interservice.querymodel.RelationFilter;
import com.founder.interservice.querymodel.TrackFilter;

import java.util.List;
import java.util.Map;

/**
 * @ClassName： DataService
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-15 13:47
 * @Version: 1.0
 */
public interface DataService {

    public List<Relation> queryRelationsList(RelationFilter relationFilter) throws Exception;

    public Map<String,Object> queryTracksList(TrackFilter trackFilter) throws Exception;

    public List<Track> queryTrackBefore5(TrackFilter trackFilter) throws  Exception;

    public List<Track> queryTracksByRSD(TrackFilter trackFilter) throws Exception;

    public List<Track> queryTracksListNoPage(TrackFilter trackFilter) throws Exception;

    public List<Track> queryNewLocation(TrackFilter trackFilter) throws InterServiceException;

    public List<Track> queryTrackByRSD(TrackFilter trackFilter) throws Exception;
}
