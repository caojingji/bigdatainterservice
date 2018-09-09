package com.founder.interservice.Exception;

import com.founder.interservice.enums.ResultEnum;

/**
 * @ClassName： InterServiceException
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 21:19
 * @Version: 1.0
 */
public class InterServiceException extends RuntimeException {

    private Integer code;

    public InterServiceException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public InterServiceException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
