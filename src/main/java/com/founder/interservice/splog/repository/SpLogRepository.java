package com.founder.interservice.splog.repository;

import com.founder.interservice.splog.model.SpLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpLogRepository extends JpaRepository<SpLog,String>,JpaSpecificationExecutor<SpLog> {
}
