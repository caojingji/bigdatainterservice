package com.founder.interservice.tracktraveltogether.vo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName： TogetherTaskResult
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 21:54
 * @Version: 1.0
 */
@Data
public class TogetherTaskResultVO {

    private String taskId;
    private String objectType;
    private String objectTypeName;
    private String objectValue;
    private String zjhm; //证件号码
    private String csdDzmc;// 出生地地址名称
    private String xzzDzmc; //现住址地址名称
    private String name; //名称
    private String csrq; //出生日期
    private String ryzp; //人员照片
    private int age; //年龄
    private String sjhm;
    private Date djsj;
    private int count;

}
