package com.founder.interservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by caopeng on 2018/6/7.
 */
@Component
public class StartConfig implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("接口服务项目启动完毕！");
    }
}
