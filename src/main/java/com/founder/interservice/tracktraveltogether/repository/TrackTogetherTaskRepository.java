package com.founder.interservice.tracktraveltogether.repository;

import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName： TrackTogetherTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:40
 * @Version: 1.0
 */
@Transactional//事物
public interface TrackTogetherTaskRepository extends JpaRepository<TrackTogetherTask,String>,JpaSpecificationExecutor<TrackTogetherTask> {
    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据
    @Query(value = "update TB_ST_TRACKTOGETHERTASK ts set ts.progress = ?1,ts.state= ?2 where ts.task_id = ?3 ", nativeQuery = true)
    public void updaxzxtatusByTaskId(String progress,String state,String taskId);

    public TrackTogetherTask findAllByTaskId(String taskId);
}
