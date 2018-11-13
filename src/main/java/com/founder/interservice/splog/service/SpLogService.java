package com.founder.interservice.splog.service;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.splog.model.SpLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpLogService {
    public void saveSpLog(SpLog spLog) throws Exception;
    public Page<SpLog> querySpLogs(SpLog spLog) throws InterServiceException;
}
