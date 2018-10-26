package com.founder.interservice.loginlog.service.impl;

import com.founder.interservice.loginlog.mapper.LoginLogMapper;
import com.founder.interservice.loginlog.model.LoginLog;
import com.founder.interservice.loginlog.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public String saveLoginLog(LoginLog loginLog) {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        uuid = uuid.substring(0,uuid.length()-2);
        loginLog.setXxzjbh(uuid);
        loginLogMapper.saveLoginLog(loginLog);

        String selectUuid = loginLogMapper.selectLoginLogUser(uuid);

        if(selectUuid.equals(uuid)){
            return "记录成功！";
        }else{
            return "记录失败！";
        }
    }
}
