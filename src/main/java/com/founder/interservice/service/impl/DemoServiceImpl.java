package com.founder.interservice.service.impl;

import com.founder.interservice.service.DemoService;
import oracle.sql.DATE;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String test() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyy-mm-dd HH:mm:ss");
        Date date = new Date();
        String result = formatter.format(date);
        return result;
    }
}
