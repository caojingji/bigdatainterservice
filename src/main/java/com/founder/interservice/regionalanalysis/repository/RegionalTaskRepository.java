package com.founder.interservice.regionalanalysis.repository;

import com.founder.interservice.regionalanalysis.model.RegionalTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @ClassName： RegionalTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 14:31
 * @Version: 1.0
 */
public interface RegionalTaskRepository extends JpaRepository<RegionalTask,String> {
    @Transactional//事物
    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据
    @Query(value = "update TB_ST_REGIONALTASK ts set ts.progress = '1',ts.state= 'FINISHED' where ts.task_id = ?1 ", nativeQuery = true)
    public int updaxzxtatusByTaskId(String taskId);

    public RegionalTask findAllByTaskId(String taskId);
}
