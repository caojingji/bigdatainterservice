package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName： TrackTogetherMapper
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:22
 * @Version: 1.0
 */
@Repository
public interface TrackTogetherMapper {
    /**
     * 根据案事件编号和服务标识号查询伴随任务列表
     * @param taskParam
     * @return
     */
    public List<TrackTogetherTask> queryTasksByAsjbhAndFwbsh(TrackTogetherTask taskParam);
}
