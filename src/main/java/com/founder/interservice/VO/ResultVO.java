package com.founder.interservice.VO;

import lombok.Data;

/**
 * @ClassName： ResultVO
 * @Auther： 曹鹏
 * @Description: 返回json格式结果字符串
 * @CreateDate： 2018-08-12 23:23
 * @Version: 1.0
 */
@Data
public class ResultVO<T> {
    /* 结果码 */
    private Integer code;
    /* 提示信息 */
    private String msg;
    /* 具体内容 */
    private T data;


}
