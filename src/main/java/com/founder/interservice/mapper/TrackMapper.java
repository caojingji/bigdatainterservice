package com.founder.interservice.mapper;

import com.founder.interservice.model.Track;
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
     * @param track
     * @return
     */
    public List<Track> getTracks(Track track) throws Exception;
}
