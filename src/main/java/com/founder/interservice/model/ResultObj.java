package com.founder.interservice.model;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

/**
 * @ClassName： ResultObj
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-15 11:30
 * @Version: 1.0
 */
@Data
public class ResultObj<T> {
    private String objType;
    private String objTypeName;
    private String objValue;
    private List<T> cphms;
    private List<T> sjhms;
    private JSONArray qqhms;
    private JSONArray wxhms;
}
