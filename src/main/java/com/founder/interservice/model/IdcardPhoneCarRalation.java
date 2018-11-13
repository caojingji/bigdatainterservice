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
@Table(name = "TB_ST_IDCARDPHONECAR")
public class IdcardPhoneCarRalation {

    @Id
    private String xxzjbh;//信息主键编号，主键
    private String asjbh; //案事件编号
    private String sfzh; //身份证号
    private String jh;//警号
    private String idcard;//身份证号码
    private String phone;//电话号
    private String car;//车牌号
    @Column(insertable = false)
    private Date djsj;//登记时间

}
