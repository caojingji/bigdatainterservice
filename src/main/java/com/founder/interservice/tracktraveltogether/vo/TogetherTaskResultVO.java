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
    private String sjhm;
    private Date djsj;
    private int count;

}
