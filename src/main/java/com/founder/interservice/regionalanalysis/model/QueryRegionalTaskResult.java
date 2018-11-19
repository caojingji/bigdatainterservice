package com.founder.interservice.regionalanalysis.model;

import lombok.Data;

import java.util.Date;
@Data
public class QueryRegionalTaskResult {

    private String taskId;
    private String objType;
    private String djsjStart;
    private String djsjEnd;
    private String objValue;
    private int startNum;
    private int endNum;
    private int page;
    private int rows;

}
