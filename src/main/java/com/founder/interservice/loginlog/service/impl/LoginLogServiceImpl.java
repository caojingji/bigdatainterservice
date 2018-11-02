package com.founder.interservice.loginlog.service.impl;

import com.founder.interservice.mapper.xzxt.LoginLogMapper;
import com.founder.interservice.loginlog.model.LoginLog;
import com.founder.interservice.loginlog.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<String,Object> selectLoginLog(LoginLog loginLog){
        List<LoginLog> loginLogs = loginLogMapper.selectLoginLog(loginLog);
        int total = loginLogMapper.getLoginLogCount(loginLog);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("loginLogs", loginLogs);

        return resultMap;
    }
}
