package com.founder.interservice.VO;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @ClassName： TrackVO
 * @Auther： 曹鹏
 * @Description: Track的View Object映射类
 * @CreateDate： 2018-08-13 13:34
 * @Version: 1.0
 */
@Data
public class TrackVO {

    private String address; //基站地址
    private String objecttypename; //对象类型名称
    private String objectvalue; //对象类型值
    private String timestr; //格式化后的时间
    private String j; //经度
    private String w; //纬度

}
