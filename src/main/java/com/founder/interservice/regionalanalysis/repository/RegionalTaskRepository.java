package com.founder.interservice.regionalanalysis.repository;

import com.founder.interservice.regionalanalysis.model.RegionalTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName： RegionalTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 14:31
 * @Version: 1.0
 */
@Transactional//事物
public interface RegionalTaskRepository extends JpaRepository<RegionalTask,String>,JpaSpecificationExecutor<RegionalTask> {
    @Modifying(clearAutomatically = true) //自动清除实体里保存的数据
    @Query(value = "update TB_ST_REGIONALTASK ts set ts.progress = ?1,ts.state= ?2 where ts.task_id = ?3 ", nativeQuery = true)
    public int updaxzxtatusByTaskId(String progress,String state,String taskId);

    public RegionalTask findAllByTaskId(String taskId);
}
