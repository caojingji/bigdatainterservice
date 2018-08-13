package com.founder.interservice.model;

/**
 * 对象关系
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class Relation {
    @Id
    private String xxzjbh;
    private String objectfromtype;  //参数对象类型编号
    private String objectfromtypename; //参数对象类型名称
    private String objectfromvalue; //参数对象类型值
    private String objecttotype; //对象类型编号
    private String objecttotypename; //对象类型名称
    private String objecttovalue; //对象类型值
    private String relativetype; //关系类型编号
    private String relativetypename; //关系类型名称
    private String source; //资源编号
    private String sourcename; //资源名称
    private String source_md5;
    private String timestamp; //时间
    private String first_timestamp; //第一次发现时间
    private String count;
    @Column(insertable = false)
    private Date djsj;
}
