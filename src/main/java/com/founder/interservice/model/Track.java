package com.founder.interservice.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class Track {
    @Id
    private String xxzjbh;
    private String address; //基站地址
    private String base;
    private String objecttype; //对象类型编号
    private String objecttypename; //对象类型名称
    private String objectvalue; //对象类型值
    private String source;  //资源类型编号
    private String sourcename; //资源类型名称
    private String source_md5; //资源编码
    private String timestamp; //时间
    private String timestr; //格式化后的时间
    private String j; //经度
    private String w; //纬度
    @Column(insertable = false)
    private Date djsj;
}
