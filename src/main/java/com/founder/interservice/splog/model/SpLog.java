package com.founder.interservice.splog.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 审批日志实体类
 */
@Entity
@DynamicUpdate
@Data
@Table(name = "TB_ST_SPLOG")
public class SpLog {
    @Id
    private String xxzjbh;//信息主键编号，主键
    private String spTitle; //审批标题
    private String cqrSfzh;//查询人身份证号
    private String cqrXm;//查询人姓名
    private String cqrJh;//查询警号
    private String cqrLxdh;//查询人联系电话
    private String dldwdm;//登录单位代码
    private String dldwmc;//登录单位名称
    private String spbsh;//查询标识号
    private String bshlxdm;//标识号类型代码
    private String bshlxmc;//标识号类型名称
    private String asjbh;//案事件编号
    private String spzt; //审批状态 0退回 1通过 2正在审批 -1未发起审批
    @Column(insertable = false)
    private Date djsj;//登记时间
    @Transient
    private int page;
    @Transient
    private int size;
    private Date startTime;
    private Date endTime;
}
