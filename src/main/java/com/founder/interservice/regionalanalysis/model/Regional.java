package com.founder.interservice.regionalanalysis.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName： Regional
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 14:17
 * @Version: 1.0
 */
@Entity
@DynamicUpdate
@Data
@Table(name = "TB_ST_REGIONAL")
public class Regional {

    @Id
    private String regionalId;//唯一标识
    private String taskId; //所属任务编号
    private Date startTime; //开始时间
    private Date endTime;//结束时间
    private String lc ;//组成区域的经纬度
    private String name; //区域名称
    private String source;//数据源
    @Column(insertable = false)
    private Date djsj; //登记时间

}
