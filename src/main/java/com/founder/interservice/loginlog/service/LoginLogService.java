package com.founder.interservice.loginlog.service;

import com.founder.interservice.loginlog.model.LoginLog;

/**
 * @ClassName： LoginLogService
 * @Auther： 徐世洪
 * @Description: java类作用描述
 * @CreateDate： 2018-10-24
 * @Version: 1.0
 */
public interface LoginLogService {

    /**
     * 记录登陆信息
     * @param loginLog
     * @return String
     */
    public String saveLoginLog(LoginLog loginLog);
}
