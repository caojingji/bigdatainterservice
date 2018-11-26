package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
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
public interface TogetherTaskResultMapper {
    /**
     * 根据任务编号查询伴随任务列表
     * @param taskParam
     * @return
     */
    public List<TogetherTaskResult> getTogetherTaskResultList(TogetherTaskResult taskParam);

    /**
     * 根据任务编号查询伴随任务列表总数
     * @param taskParam
     * @return
     */
    int getTogetherTaskResultListTotalCount(TogetherTaskResult taskParam);
}
