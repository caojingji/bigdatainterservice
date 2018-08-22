package com.founder.interservice.regionalanalysis.repository;

import com.founder.interservice.regionalanalysis.model.RegionalTask;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName： RegionalTaskRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 14:31
 * @Version: 1.0
 */
public interface RegionalTaskRepository extends JpaRepository<RegionalTask,String> {
}
