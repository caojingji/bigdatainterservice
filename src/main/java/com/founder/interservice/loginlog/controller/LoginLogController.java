package com.founder.interservice.loginlog.controller;

import com.founder.interservice.loginlog.model.LoginLog;
import com.founder.interservice.loginlog.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 记录登录用户信息
 * @ClassName： LoginLogController,
 * @Auther： 徐世洪
 * @Description: 日志记录controller
 * @CreateDate： 2018-10-24
 * @Version: 1.0
 */
@Controller
public class LoginLogController {
    @Autowired
    LoginLogService loginLogService;

    /**
     * 登录日志记录
     * @param loginLog
     * @return
     */
    @RequestMapping(value ="/saveLoginLog",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String saveLoginLog(LoginLog loginLog){
        String result = loginLogService.saveLoginLog(loginLog);
        return result;
    }

}
