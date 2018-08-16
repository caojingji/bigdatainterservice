package com.founder.interservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.founder.interservice.mapper")
public class BigdatainterserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigdatainterserviceApplication.class, args);
    }
}
