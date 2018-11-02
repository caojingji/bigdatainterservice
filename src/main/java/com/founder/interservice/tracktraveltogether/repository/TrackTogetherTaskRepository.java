package com.founder.interservice.tracktraveltogether.repository;

import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @ClassName： TrackTogetherTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:40
 * @Version: 1.0
 */
public interface TrackTogetherTaskRepository extends JpaRepository<TrackTogetherTask,String> {
    @Transactional//事物
    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据
    @Query(value = "update TB_ST_TRACKTOGETHERTASK ts set ts.progress = '1',ts.state= 'FINISHED' where ts.task_id = ?1 ", nativeQuery = true)
    public void updaxzxtatusByTaskId(String taskId);

    public TrackTogetherTask findAllByTaskId(String taskId);
}
