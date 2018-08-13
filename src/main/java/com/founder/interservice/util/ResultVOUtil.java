package com.founder.interservice.util;

import com.founder.interservice.VO.ResultVO;

/**
 * @ClassName： ResultVOUtil
 * @Auther： 曹鹏
 * @Description: 返回结果格式化工具类
 * @CreateDate： 2018-08-12 23:25
 * @Version: 1.0
 */
public class ResultVOUtil {
    /**
     *
     * @Description:调用结果成功
     * @Param:
     * @param o 结果集合
     * @return: com.founder.interservice.VO.ResultVO
     * @Author: cao peng
     * @date: 2018/8/12 0012-23:30
     */
    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(o);
        resultVO.setCode(0);
        resultVO.setMsg("成功！");
        return resultVO;
    }
    /**
    *
    * @Description: 调用成功，但没有结果集
    * @Param:
        * @param null
    * @return:
    * @Author: cao peng
    * @date: 2018/8/12 0012-23:33
    */
    public static ResultVO success(){
        return success(null);
    }
    /**
     *
     * @Description: 调用接口失败 发生错误
     * @Param:
     * @param code 错误码
     * @param msg 错误信息
     * @return: com.founder.interservice.VO.ResultVO
     * @Author: cao peng
     * @date: 2018/8/12 0012-23:35
     */
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}
