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
    OBJTYPE_PARAM_ERROR(2,"参数类型必传！"),

    PARAM_NOTNULL(3,"参数值必传！"),

    IPHONE_PARAM_ERROE(4,"手机号码应为11位数！"),
    IPHONE_PARAM_ERROR1(5,"手机号码不正确！"),

    QQ_PARAM_ERROR(6,"QQ号码格式不正确"),

    TIME_PARAM_NOTNULL(7,"起止时间必传！"),
    TIME_PARAM_ERROR(8,"时间格式错误！"),
    TIME_APRAM_ERROR2(9,"结束时间必须大于开始时间！"),
    RESULT_ERROR(55,"发生了未知错误！"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
