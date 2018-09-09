package com.founder.interservice.tracktraveltogether.controller;

import com.founder.interservice.Exception.InterServiceException;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import com.founder.interservice.tracktraveltogether.service.TrackTogetherService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.ResultVOUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @ClassName： TrackTogetherContoller
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:21
 * @Version: 1.0
 */
@Controller
@CrossOrigin  //跨域访问
public class TrackTogetherContoller {

    private TrackTogetherService trackTogetherService;

    /**
     *
     * @Description: 发送伴随任务
     * @Param:
     * @param trackParam 任务对象
     * @param startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间 yyyy-MM-dd HH:mm:ss
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cao peng
     * @date: 2018/9/9 0009-20:30
     */
    @RequestMapping(value = "sendTrackTogetherTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultVO sendTrackTogetherTask(TrackTogetherTask trackParam,String startTime,String endTime){
        ResultVO resultVO = null;
        try {
            if(StringUtil.ckeckEmpty(trackParam.getObjectType())){
                trackParam.setObjectType("4314");
            }
            if(!StringUtil.ckeckEmpty(startTime)){
                Date kssj = startTime.contains(" ") ? DateUtil.convertStringToDateTime(startTime): DateUtil.convertStringToDate(startTime);
                trackParam.setStartTime(kssj);
            }else{
                throw new InterServiceException(ResultEnum.PARAM_NOTNULL);
            }
            if(!StringUtil.ckeckEmpty(endTime)){
                Date jssj = startTime.contains(" ") ? DateUtil.convertStringToDateTime(startTime) : DateUtil.convertStringToDate(startTime);
                trackParam.setStartTime(jssj);
            }else{
                throw new InterServiceException(ResultEnum.PARAM_NOTNULL);
            }
            String taskId = trackTogetherService.sendTrackTogetherTask(trackParam); //发送任务 并且得到任务编号
            if(!StringUtil.ckeckEmpty(taskId)){
                trackParam.setTaskId(taskId);
            }else{
                throw new InterServiceException(ResultEnum.TASK_SEND_ERROR);
            }
            trackTogetherService.saveTogetherTask(trackParam);

            resultVO = ResultVOUtil.success(taskId);
        }catch (InterServiceException e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return resultVO;
    }


}
