package com.founder.interservice.splog.repository;

import com.founder.interservice.splog.model.SpLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpLogRepository extends JpaRepository<SpLog,String> {
}
