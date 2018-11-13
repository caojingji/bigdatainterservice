package com.founder.interservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事务管理
@EnableScheduling //开启定时任务
@MapperScan({"com.founder.interservice.mapper","com.founder.interservice.*.mapper"})
public class BigdatainterserviceApplication{

    public static void main(String[] args) {
        SpringApplication.run(BigdatainterserviceApplication.class, args);
    }
}
