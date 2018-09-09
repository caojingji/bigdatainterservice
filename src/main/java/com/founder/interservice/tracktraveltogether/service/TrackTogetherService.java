package com.founder.interservice.tracktraveltogether.service;

import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;

/**
 * @ClassName： TrackTogetherService
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:22
 * @Version: 1.0
 */
public interface TrackTogetherService {
    public String sendTrackTogetherTask(TrackTogetherTask trackParam);

    public void saveTogetherTask(TrackTogetherTask trackParam);
}
