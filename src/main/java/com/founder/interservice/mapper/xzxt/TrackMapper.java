package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.Track;
import com.founder.interservice.querymodel.TrackFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackMapper {
    /**
     * 添加
     * @param track
     * @return
     */
    public String insertTrack(Track track) throws Exception;

    /**
     * 批量插入
     * @param tracks
     * @throws Exception
     */
    public void insertTracks(List<Track> tracks) throws Exception;

    /**
     * 查询
     * @param trackFilter
     * @return
     */
    public List<Track> getTracks(TrackFilter trackFilter) throws Exception;

    /**
     * 查询总数
     * @param trackFilter
     * @return
     */
    public int getTracksTotalCount(TrackFilter trackFilter) throws Exception;
    /**
    *
    * @Description: 查询次数最多的前五轨迹
    * @Param:
        * @param trackFilter 查询条件
    * @return:
    * @Author: cao peng
    * @date: 2018/8/16 0016-9:41
    */
    public List<Track> queryTrackBefore5(TrackFilter trackFilter) throws Exception;

    public List<Track> queryTracksListNoPage(TrackFilter trackFilter) throws Exception;

    public List<Track> queryTracksByRSD(TrackFilter trackFilter) throws Exception;

    public List<Track> queryNewLocation(TrackFilter trackFilter) throws InterServiceException;

    public List<Track> queryTrackByRSD(TrackFilter trackFilter) throws Exception;
}
