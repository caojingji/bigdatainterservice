package com.founder.interservice.recordLog.service.impl;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.recordLog.mapper.RecordLogMapper;
import com.founder.interservice.recordLog.model.Querylog;
import com.founder.interservice.recordLog.service.RecordLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @ClassName： RecordLogServiceImpl
 * @Auther： 罗启豪
 * @Description: 区域碰撞service实现类
 * @CreateDate： 2018-10-17
 * @Version: 1.0
 */
@Service
public class RecordLogServiceImpl implements RecordLogService {
    @Autowired
    private RecordLogMapper recordLogMapper;

    @Override
    public void saveQueryLog(Querylog querylog) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        uuid = uuid.substring(0,uuid.length()-2);
        querylog.setXxzjbh(uuid);
        recordLogMapper.saveQueryLog(querylog);
    }
}
