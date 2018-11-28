package com.founder.interservice.tracktraveltogether.model;

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
@Entity
@DynamicUpdate
@Table(name = "TB_ST_TOGETHERTASKRESULT")
public class TogetherTaskResult {

    @Id
    private String XXZJBH;
    private String taskId;
    private String objectType;
    private String objectTypeName;
    private String objectValue;
    @Transient
    private String djsjStart;
    @Transient
    private String djsjEnd;
    @Column(insertable = false)
    private Date djsj;
    private int count;
    @Transient
    private int startNum;
    @Transient
    private int endNum;

}
