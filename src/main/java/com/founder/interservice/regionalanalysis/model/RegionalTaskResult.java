package com.founder.interservice.regionalanalysis.model;

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
@Entity
@DynamicUpdate
@Table(name = "TB_ST_REGIONALTASKRESULT")
public class RegionalTaskResult {

    @Id
    private String XXZJBH;
    private String taskId;
    private String objectType;
    private String objectTypeName;
    private String objectValue;
    private Date djsj;

}
