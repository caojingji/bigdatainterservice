package com.founder.interservice.loginlog.controller;

import com.founder.interservice.loginlog.model.LoginLog;
import com.founder.interservice.loginlog.service.LoginLogService;
import com.founder.interservice.util.EasyUIPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询登录日志
     */
    @RequestMapping(value = "/selectLoginLog", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object>  selectLoginLog(LoginLog loginLog,
                                   @RequestParam(value = "page",defaultValue = "0") int page,
                                   @RequestParam(value = "rows",defaultValue = "0") int rows){
        Map<String,Object> objectMap = new HashMap<>();
        List<LoginLog> loginLogVOS = null;
        try{
            EasyUIPage easyUIPage = new EasyUIPage();
            easyUIPage.setPage(page);
            easyUIPage.setPagePara(rows);
            int begin = easyUIPage.getBegin();
            int end = easyUIPage.getEnd();

            loginLog.setCxrJssj(loginLog.getCxrJssj());
            loginLog.setBegin(begin);
            loginLog.setEnd(end);
            
            Map<String,Object> resultMap = loginLogService.selectLoginLog(loginLog);
            int total = (int)resultMap.get("total");
            List<LoginLog> loginLogs = (List<LoginLog>)resultMap.get("loginLogs");
            if(loginLogs != null && !loginLogs.isEmpty()){
                loginLogVOS = new ArrayList<>();
                for (LoginLog loginlog: loginLogs) {
                    LoginLog loginLogVO = new LoginLog();
                    BeanUtils.copyProperties(loginlog,loginLogVO);
                    loginLogVOS.add(loginLogVO);
                }
                objectMap.put("rows", loginLogVOS);
                objectMap.put("total", total);
            } else{
                objectMap.put("total", 0);
                objectMap.put("rows", new ArrayList<>());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return objectMap;
    }

    @RequestMapping(value = "/selectLoginLogPage",method = {RequestMethod.GET,RequestMethod.POST})
    public String getSelectLoginLogPage(){
        return "LoginLog/loginlog";
    }
}
