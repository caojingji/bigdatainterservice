package com.founder.interservice.tracktraveltogether.vo;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 伴随任务实体的VO类
 */
@Data
public class TrackTogetherTaskVO {
    private String taskId; //任务编号
    private String objectValue; //对象值
    private String taskName; //任务名称
    private String taskCaseId; //案件编号
    private String state; //任务状态
    private Date startTime; //开始时间
    private Date endTime; //结束时间
    private Date djsj; //登记时间
}
