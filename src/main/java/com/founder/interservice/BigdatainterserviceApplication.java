package com.founder.interservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.founder.interservice.mapper")
public class BigdatainterserviceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BigdatainterserviceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BigdatainterserviceApplication.class, args);
    }
}
