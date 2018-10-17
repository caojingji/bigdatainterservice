package com.founder.interservice.recordLog.controller;

import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.recordLog.model.Querylog;
import com.founder.interservice.recordLog.service.RecordLogService;
import com.founder.interservice.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @ClassName： RecordLogController
 * @Auther： 罗启豪
 * @Description: 日志记录controller
 * @CreateDate： 2018-10-17
 * @Version: 1.0
 */
@Controller
public class RecordLogController {

    @Autowired
    private RecordLogService recordLogService;

    @RequestMapping(value = "/saveQueryLog",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO saveQueryLog(Querylog querylog){
        ResultVO resultVO = null;
        try {
            recordLogService.saveQueryLog(querylog);
            resultVO = ResultVOUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResultEnum.RESULT_ERROR.getCode(),ResultEnum.RESULT_ERROR.getMessage());
        }

        return resultVO;
    }
}
