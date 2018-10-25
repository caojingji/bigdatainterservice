package com.founder.interservice.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
@Table(name = "TB_ST_PHONEQQWECHAT")
public class PhoneQQWeChatRalation {
    @Id
    private String xxzjbh;//信息主键编号，主键
    private String phone;//电话号
    private String qq;//QQ号
    private String wechat;//微信号
    @Column(insertable = false)
    private Date djsj;//登记时间

}
