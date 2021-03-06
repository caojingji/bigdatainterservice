package com.founder.interservice.enums;


import com.founder.interservice.service.IphoneTrackService;
import lombok.Getter;

/**
 * 返回结果枚举常量
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    YHCATE_PARAM_NOTNULL(1,"yhCate参数必传！"),
    YHCATE_PARAM_ERROR(11,"yhCate参数错误！"),
    OBJTYPE_PARAM_NOTNULL(2,"objType参数必传！"),
    OBJTYPE_PARAM_ERROR(10,"objType参数错误！"),

    OBJVALUE_PARAM_NOTNULL(3,"objValue参数必传！"),
    PARAM_NOTNULL(13,"缺少必须的参数！"),
    PARAM_ERROR(12,"参数错误！"),
    IPHONE_PARAM_ERROE(4,"手机号码应为11位数！"),
    IPHONE_PARAM_ERROR1(5,"手机号码不正确！"),

    QQ_PARAM_ERROR(6,"QQ号码格式不正确"),

    TIME_PARAM_NOTNULL(7,"起止时间必传！"),
    TIME_PARAM_ERROR(8,"时间格式错误！"),
    TIME_APRAM_ERROR2(9,"结束时间必须大于开始时间！"),

    TASK_SEND_ERROR(22,"任务发送失败！"),
    REQUEST_URL_ERROR(23,"请求远程地址错误！"),

    RESULT_ERROR(55,"发生了未知错误！"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
