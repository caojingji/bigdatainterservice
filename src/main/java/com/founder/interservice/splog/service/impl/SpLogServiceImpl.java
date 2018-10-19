package com.founder.interservice.splog.service.impl;

import com.founder.interservice.splog.model.SpLog;
import com.founder.interservice.splog.repository.SpLogRepository;
import com.founder.interservice.splog.service.SpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpLogServiceImpl implements SpLogService {

    @Autowired
    private SpLogRepository spLogRepository;
    @Override
    public void saveSpLog(SpLog spLog) throws Exception {
        try{
            spLogRepository.save(spLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
