package com.founder.interservice.tracktraveltogether.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.regionalanalysis.VO.RegionalTaskResultVO;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import com.founder.interservice.tracktraveltogether.service.TrackTogetherService;
import com.founder.interservice.tracktraveltogether.vo.TogetherTaskResultVO;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.ResultVOUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private TrackTogetherService trackTogetherService;
    @Autowired
    private IphoneTrackService iphoneTrackService; //调取网安数据接口

    /**
    * 
    * @Description: 跳转到时空轨迹伴随结果展示
    * @Param:
    * @return: ModelAndView
    * @Author: cao peng
    * @date: 2018/9/18 0018-10:19
    */
    @RequestMapping(value = "/toShowResultJsp")
    public ModelAndView toShowResultJsp(){
        ModelAndView modelAndView = new ModelAndView("qybs/skgjbsjgzs");
        return modelAndView;
    }



    /**
     *
     * @Description: 根据任务编号查看伴随任务的进行状态
     * @Param:
     * @param taskId 任务编号
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: cao peng
     * @date: 2018/9/17 0017-10:09
     */
    @RequestMapping(value = "/getTogetherTaskState")
    @ResponseBody
    public JSONObject getTogetherTaskState(String taskId){
        JSONObject jsonObject = new JSONObject();
        try{
            TrackTogetherTask task = trackTogetherService.findByTaskId(taskId);
            if(null != task){
                jsonObject.put("state", task.getState());
                jsonObject.put("progress", task.getProgress());
            }else{
                jsonObject.put("state", "");
                jsonObject.put("progress", "");
            }
        }catch (InterServiceException e){
            e.printStackTrace();
            jsonObject.put("state", "");
            jsonObject.put("progress", "");
        }
        return jsonObject;
    }


    /**
     *
     * @Description: 通过任务编号查询任务结果
     * @Param:
     * @param taskId  任务编号
     * @return: java.util.List<com.founder.interservice.tracktraveltogether.vo.TogetherTaskResultVO>
     * @Author: cao peng
     * @date: 2018/9/11 0011-21:31
     */
    @RequestMapping(value = "/getTogetherTaskResults",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<TogetherTaskResultVO> getTaskResults(String taskId){
        List<TogetherTaskResultVO> taskResultVOS = new ArrayList<>();
        try {
            List<TogetherTaskResult> taskResults = trackTogetherService.findTogetherTaskResult(taskId);
            if(taskResults != null && !taskResults.isEmpty()){
                for(int i = 0; i <= taskResults.size() - 1; i++){
                    TogetherTaskResultVO taskResultVO = new TogetherTaskResultVO();
                    BeanUtils.copyProperties(taskResults.get(i),taskResultVO);
                    if(!StringUtil.ckeckEmpty(taskResultVO.getObjectValue())){
                        JSONObject jsonObject = iphoneTrackService.getObjectRelation(taskResultVO.getObjectValue());
                        if(jsonObject != null){
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            if(jsonArray != null && jsonArray.size() > 0){
                                for (int j = 0; j <= jsonArray.size() - 1;j++){
                                    JSONObject resObj = jsonArray.getJSONObject(j);
                                    String relativeType = resObj.getString("relativeType"); //关联类型  4402（手机-IMSI）
                                    String objectFromType = resObj.getString("objectFromType"); //数据来源值类型  4314(IMSI)
                                    String objectFromValue = resObj.getString("objectFromValue"); //数据来源值
                                    String objectToType = resObj.getString("objectToType"); //所得对象值类型  20（联系方式） 4394（电话号码） 3996（手机号码）
                                    if ("4402".equals(relativeType) && "4314".equals(objectFromType)
                                            && Arrays.asList("20","4394","3996").contains(objectToType) && taskResultVO.getObjectValue().equals(objectFromValue)){
                                        String sjhm = resObj.getString("objectToValue");
                                        if(null != sjhm && 11 == sjhm.length()){
                                            taskResultVO.setSjhm(sjhm); //将得到手机号码赋值到对象
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    taskResultVOS.add(taskResultVO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taskResultVOS;
    }


    /**
     *
     * @Description: 发送伴随任务
     * @Param:
     * @param *objectValue 手机号码
     * @param *taskName 任务名称
     * @param *taskCaseId 案件编号
     * @param *startTime 开始时间 yyyy-MM-dd HH:mm:ss
     * @param *endTime 结束时间 yyyy-MM-dd HH:mm:ss
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cao peng
     * @date: 2018/9/9 0009-20:30
     */
    @RequestMapping(value = "/sendTrackTogetherTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultVO sendTrackTogetherTask(HttpServletRequest request){
        ResultVO resultVO = null;
        try {
            TrackTogetherTask trackParam = new TrackTogetherTask();
            String startTime =request.getParameter("startTime");
            String endTime =request.getParameter("endTime");
            trackParam.setObjectValue(request.getParameter("objectValue"));
            trackParam.setTaskName(request.getParameter("taskName"));
            trackParam.setTaskCaseId(request.getParameter("taskCaseId"));

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
                trackParam.setEndTime(jssj);
            }else{
                throw new InterServiceException(ResultEnum.PARAM_NOTNULL);
            }
            //1 现使用手机号码调出imsi
            if(!StringUtil.ckeckEmpty(trackParam.getObjectValue())){
                JSONObject jsonObject = iphoneTrackService.getObjectRelation(trackParam.getObjectValue());
                if(jsonObject != null){
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    if(jsonArray != null && jsonArray.size() > 0){
                        for (int j = 0; j <= jsonArray.size() - 1;j++){
                            JSONObject resObj = jsonArray.getJSONObject(j);
                            String relativeType = resObj.getString("relativeType"); //关联类型  4402（手机-IMSI）
                            String objectFromType = resObj.getString("objectFromType"); //数据来源值类型  20（联系方式） 4394（电话号码） 3996（手机号码）
                            String objectFromValue = resObj.getString("objectFromValue"); //数据来源值
                            String objectToType = resObj.getString("objectToType"); //所得对象值类型  4314(IMSI)
                            if ("4402".equals(relativeType) && "4314".equals(objectToType)
                                    && Arrays.asList("20","4394","3996").contains(objectFromType) && trackParam.getObjectValue().equals(objectFromValue)){
                                String imsi = resObj.getString("objectToValue");
                                if(!StringUtil.ckeckEmpty(imsi)){
                                    trackParam.setObjectValue(imsi); //将得到手机号码赋值到对象
                                    break;
                                }
                            }
                        }
                    }
                }
            }else{
                throw new InterServiceException(ResultEnum.PARAM_NOTNULL);
            }
            //trackParam.setObjectValue("42151645456456");
            if(!StringUtil.ckeckEmpty(trackParam.getObjectValue())){
                //2 拿到imsi后 再去调用伴随接口
                String taskId = trackTogetherService.sendTrackTogetherTask(trackParam); //发送任务 并且得到任务编号
                //String taskId = "123123123";
                if(!StringUtil.ckeckEmpty(taskId)){
                    trackParam.setTaskId(taskId);
                }else{
                    throw new InterServiceException(ResultEnum.TASK_SEND_ERROR);
                }
                trackParam.setProgress("0");
                trackParam.setState("QUEUEING");
                trackTogetherService.saveTogetherTask(trackParam);
                resultVO = ResultVOUtil.success(taskId);
            }else{
                throw new InterServiceException(ResultEnum.PARAM_NOTNULL);
            }
        }catch (InterServiceException e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return resultVO;
    }


}
