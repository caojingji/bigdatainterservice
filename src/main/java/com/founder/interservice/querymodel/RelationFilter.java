package com.founder.interservice.querymodel;

/**
 * 对象关系
 */

import lombok.Data;
import java.util.Date;

@Data
public class RelationFilter {

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
    private String kssj;
    private String jssj;
    private Date djsj;
}
