package com.founder.interservice.regionalanalysis.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName： RegionalTask
 * @Auther： 曹鹏
 * @Description:  区域碰撞任务类实体
 * @CreateDate： 2018-08-22 14:16
 * @Version: 1.0
 */
@Data
@Entity
@DynamicUpdate
@Table(name = "TB_ST_REGIONALTASK")
public class RegionalTask {
    @Id
    private String taskId;//任务编号
    private String taskName; //任务名称
    private String expression;// 碰撞表达式
    private String taskCaseId;// 案件编号
    private String progress; //任务进度 1表示百分百 完成
    private String state; //任务状态 QUEUEING 排队等待、STARTING 开始中、RUNNING 执行中、FINISHED 完成
    @Column(insertable = false)
    private Date djsj; //任务登记时间
    @Transient //单独给实体类添加属性
    private List<Regional> regionals;

}
