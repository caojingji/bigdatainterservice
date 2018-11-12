package com.founder.interservice.querymodel;

/**
 * 对象关系（方正库）
 */

import lombok.Data;

import java.util.Date;

@Data
public class RelationLocalFilter {

    private String xxzjbh;
    private String asjbh; //案事件编号
    private String sfzh; //当前登录人身份证号
    private String jh; //当前登录人警号
    private String idcard;  //身份证号码
    private String phone; //电话号
    private String car; //车牌号
    private String qq; //QQ号
    private String wechat; //微信号
    private Date djsj;//登记时间
}
