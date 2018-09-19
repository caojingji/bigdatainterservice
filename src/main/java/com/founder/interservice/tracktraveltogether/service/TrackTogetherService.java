package com.founder.interservice.tracktraveltogether.service;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import org.springframework.data.domain.Page;

import java.util.List;

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

    public List<TogetherTaskResult> findTogetherTaskResult(String taskId) throws InterServiceException;

    public TrackTogetherTask findByTaskId(String taskId) throws InterServiceException;
}
