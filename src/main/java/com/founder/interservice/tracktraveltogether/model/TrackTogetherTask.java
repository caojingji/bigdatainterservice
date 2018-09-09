package com.founder.interservice.tracktraveltogether.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName： TrackTogetherTask
 * @Auther： 曹鹏
 * @Description: 伴随任务实体类
 * @CreateDate： 2018-09-09 20:32
 * @Version: 1.0
 */
@Data
@Entity
@Table(name = "TB_ST_TRACKTOGETHERTASK")
@DynamicUpdate
public class TrackTogetherTask {
    @Id
    private String taskId; //任务编号
    private String objectValue; //对象值
    private String objectType; //对象类型
    private String taskName; //任务名称
    private String taskCaseId; //案件编号
    private String state; //任务状态
    private String progress;// 任务进度
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private Date djsj; //登记时间
}
