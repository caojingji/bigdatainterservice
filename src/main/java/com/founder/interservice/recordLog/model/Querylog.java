package com.founder.interservice.recordLog.model;

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
@Table(name = "TB_ST_QUERYLOG")
public class Querylog {
    @Id
    private String xxzjbh;//信息主键编号，主键
    private String cxrSfzh;//查询人身份证号
    private String cxrXm;//查询人姓名
    private String cxrJh;//查询警号
    private String cxrLxdh;//查询人联系电话
    private String cxbsh;//查询标识号
    private String bshlxdm;//标识号类型代码
    private String bshlxmc;//标识号类型名称
    private String asjbh;//案事件编号
    @Column(insertable = false)
    private Date djsj;//登记时间

}
