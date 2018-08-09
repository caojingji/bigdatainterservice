package com.founder.interservice.controller;

import com.founder.interservice.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *测试controller
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/test1")
    public String test(String name){
        String result = "你好！" + name + "，今天是："+demoService.test()+"!";
        return result;
    }


}
