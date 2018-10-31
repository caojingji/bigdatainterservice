package com.founder.interservice.caseinformation.model;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "gxzxt.tb_st_asj")
public class CaseInformation {

    private String asjbh;//按事件编号

    private String ajmc;//案件名称

    private Date asjfssjAsjfskssj;//犯案时间

    private String asjfsddDzmc;//犯案地点

    private String jyaq;//简要案情

}
