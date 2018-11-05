package com.founder.interservice.recordLog.VO;

import lombok.Data;

import java.util.Date;

@Data
public class RecordLogVo {

    private String cxrSfzh;//查询人身份证号
    private String cxrXm;//查询人姓名
    private String cxrJh;//查询警号
    private String cxrLxdh;//查询人联系电话
    private String cxbsh;//查询标识号
    private String bshlxdm;//标识号类型代码
    private String bshlxmc;//标识号类型名称
    private String asjbh;//案事件编号
    private String dldwdm;
    private String dldwmc;
    private Date djsj;//登记时间
}
