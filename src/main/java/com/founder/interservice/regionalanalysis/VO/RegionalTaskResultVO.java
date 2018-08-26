package com.founder.interservice.regionalanalysis.VO;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName： RegionalTaskResult
 * @Auther： 曹鹏
 * @Description: 区域碰撞任务结果值
 * @CreateDate： 2018-08-22 17:10
 * @Version: 1.0
 */
@Data
public class RegionalTaskResultVO {

    private String taskId;
    private String objectTypeName;
    private String objectValue;
    private Date djsj;
    private String zjhm; //证件号码
    private String address; //户籍地址
    private String name; //名称
    private String csrq; //出生日期
    private String ryzp; //人员照片
    private String sjhm;//手机号码
    private int age; //年龄

}
