package com.founder.interservice.service.impl;

import com.founder.interservice.mapper.RelationMapper;
import com.founder.interservice.mapper.TrackMapper;
import com.founder.interservice.model.Relation;
import com.founder.interservice.model.Track;
import com.founder.interservice.querymodel.RelationFilter;
import com.founder.interservice.querymodel.TrackFilter;
import com.founder.interservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName： DataServiceImpl
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-15 13:44
 * @Version: 1.0
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private TrackMapper trackMapper;

    /**
     *
     * @Description: 根据日时段查询每个日时段排行前五的轨迹地址
     * @Param:
     * @param trackFilter
     * @return: java.util.List<com.founder.interservice.model.Track>
     * @Author: cao peng
     * @date: 2018/8/17 0017-14:15
     */
    public List<Track> queryTracksByRSD(TrackFilter trackFilter) throws Exception{
        return trackMapper.queryTracksByRSD(trackFilter);
    }

    /**
     *
     * @Description: 查询轨迹次数最多的前五轨迹信息
     * @Param:
     * @param trackFilter 封装了查询提哦案件
     * @return: java.util.List<com.founder.interservice.model.Track>
     * @Author: cao peng
     * @date: 2018/8/16 0016-9:43
     */
    public List<Track> queryTrackBefore5(TrackFilter trackFilter) throws  Exception{
        return trackMapper.queryTrackBefore5(trackFilter);
    }

    /**
    *
    * @Description: 不分页根据条件查询Track
    * @Param:
        * @param trackFilter 查询条件
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-17:41
    */
    @Override
    public List<Track> queryTracksListNoPage(TrackFilter trackFilter) throws Exception{
        return trackMapper.queryTracksListNoPage(trackFilter);
    }

    /**
     *
     * @Description: 根据组合条件查询Relation
     * @Param:
     * @param relationFilter 参数对象
     * @return: java.util.List<com.founder.interservice.model.Relation>
     * @Author: cao peng
     * @date: 2018/8/15 0015-16:29
     */
    @Override
    public List<Relation> queryRelationsList(RelationFilter relationFilter) throws Exception {
        List<Relation> relations = relationMapper.getRelations(relationFilter);
        return relations;
    }
    /**
     *
     * @Description: 根据组合条件查询轨迹信息
     * @Param:
     * @param trackFilter 参数对象
     * @return: java.util.List<com.founder.interservice.model.Track>
     * @Author: cao peng
     * @date: 2018/8/15 0015-17:04
     */
    @Override
    public Map<String,Object> queryTracksList(TrackFilter trackFilter) throws Exception {
        List<Track> tracks = trackMapper.getTracks(trackFilter);
        int total = trackMapper.getTracksTotalCount(trackFilter);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("tracks", tracks);
        return resultMap;
    }
}
