package com.founder.interservice.repository;

import com.founder.interservice.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName： RequestLogRepositor
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-13 16:06
 * @Version: 1.0
 */
public interface RequestLogRepository extends JpaRepository<RequestLog,String> {
}
