package com.founder.interservice.tracktraveltogether.repository;

import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName： TrackTogetherTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:40
 * @Version: 1.0
 */
public interface TogetherTaskResultRepository extends JpaRepository<TogetherTaskResult,String> {
}
