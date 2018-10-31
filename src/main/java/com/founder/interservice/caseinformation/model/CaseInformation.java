package com.founder.interservice.caseinformation.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class CaseInformation {

    private String asjbh;//按事件编号

    private String ajmc;//案件名称

    private String asjfssjAsjfskssj;//犯案时间

    private String asjfsddDzmc;//犯案地点

    private String jyaq;//简要案情

}
