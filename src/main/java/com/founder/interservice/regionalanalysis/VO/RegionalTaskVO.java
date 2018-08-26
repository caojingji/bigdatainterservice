package com.founder.interservice.regionalanalysis.VO;

import com.founder.interservice.regionalanalysis.model.Regional;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @ClassName： RegionalTaskVO
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-25 15:22
 * @Version: 1.0
 */
@Data
public class RegionalTaskVO {

    private String taskId;//任务编号
    private String taskName; //任务名称
    private String expression;// 碰撞表达式
    private String taskCaseId;// 案件编号
    private String progress; //任务进度 1表示百分百 完成
    private String state; //任务状态 QUEUEING 排队等待、STARTING 开始中、RUNNING 执行中、FINISHED 完成
    private Date djsj; //任务登记时间
    private String qyCount; //区域个数
    private int startNum;
    private int endNum;

}
