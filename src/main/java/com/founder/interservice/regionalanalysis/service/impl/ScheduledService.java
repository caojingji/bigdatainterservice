package com.founder.interservice.regionalanalysis.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskResultRepository;
import com.founder.interservice.util.HttpUtil;
import com.founder.interservice.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName： ScheduledService
 * @Auther： 曹鹏
 * @Description: 定时获取任务状态和任务结果的定时类
 * @CreateDate： 2018-08-22 16:46
 * @Version: 1.0
 */
@Component
@Async
public class ScheduledService {

    @Value(value = "${wabigdata.regionalAnalysisTaskStatus.url}")
    private String REGIONAL_ANALYSIS_TASK_STATUS; //获取任务状态
    @Value(value = "${wabigdata.regionalAnalysisTaskInfo.url}")
    private String REGIONAL_ANALYSIS_TASK_INFO; //获取任务结果

    @Autowired
    private RegionalTaskRepository regionalTaskRepository;
    @Autowired
    private RegionalTaskResultRepository taskResultRepository;

    /**
     *
     * @Description: 查取任务结果的定时方法
     * @Param:

     * @return:
     * @Author: cao peng
     * @date: 2018/8/22 0022-16:35
     */
    @Scheduled(initialDelay = 5000,fixedDelay = 180000) //项目启动后延迟5秒执行，每次执行完后三分钟后再次执行
    public void queryTaskResult(){
        System.out.println("=============开始执行定时任务================");
        try{
            //1 下去查询任务表中 progress = 0  and status = QUEUEING的任务
            RegionalTask regionalTask = new RegionalTask();
            regionalTask.setState("QUEUEING");
            regionalTask.setProgress("0");
            Example<RegionalTask> taskExample = Example.of(regionalTask);
            List<RegionalTask> taskList = regionalTaskRepository.findAll(taskExample);
            if(taskList != null && !taskList.isEmpty()){
                for (RegionalTask task:taskList) {
                    String status_url = REGIONAL_ANALYSIS_TASK_STATUS + "&taskId="+task.getTaskId();
                    System.out.println("status_url = " + status_url);
                    String statusStr = HttpUtil.doGet(status_url);
                    //String statusStr = "{\"progress\":0.8,\"state\":\"TIMEOUT\"}";
                    System.out.println("statusStr ======================== " + statusStr);
                    while(statusStr == null || statusStr.isEmpty() || !statusStr.startsWith("{")){
                        statusStr = HttpUtil.doGet(status_url);
                        System.out.println("statusStr ======================== " + statusStr);
                    }
                    JSONObject jsonObject = JSONObject.parseObject(statusStr);
                    String progress = jsonObject.getString("progress");
                    String state = jsonObject.getString("state");
                    String info_url = REGIONAL_ANALYSIS_TASK_INFO + "&taskId=" + task.getTaskId();
                    String taskInfoResult = HttpUtil.doGet(info_url);
                    //String taskInfoResult = "{\"results\":[],\"status\":\"ok\"}";
                    while(taskInfoResult == null || taskInfoResult.isEmpty() || !taskInfoResult.startsWith("{")){
                        taskInfoResult = HttpUtil.doGet(info_url);
                    }
                    getAndSaveInfo(taskInfoResult,task,progress,state);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAndSaveInfo(String taskInfoResult,RegionalTask task,String progress,String state) throws  Exception{
        JSONObject o = JSONObject.parseObject(taskInfoResult);
        JSONArray jsonArray = o.getJSONArray("results");
        if(jsonArray != null && jsonArray.size() > 0){
            List<RegionalTaskResult> taskResults = jsonArray.toJavaList(RegionalTaskResult.class);
            if(taskResults != null && !taskResults.isEmpty()){
                for (RegionalTaskResult r:taskResults ) {
                    r.setTaskId(task.getTaskId());
                    r.setXXZJBH(KeyUtil.getUniqueKey("TR"));
                    r.setDjsj(new Date());
                }
                RegionalTaskResult param = new RegionalTaskResult();
                param.setTaskId(task.getTaskId());
                Example<RegionalTaskResult> example = Example.of(param);
                List<RegionalTaskResult> results = taskResultRepository.findAll(example);
                if(results == null || results.isEmpty()){
                    taskResultRepository.save(taskResults);
                }
                regionalTaskRepository.updaxzxtatusByTaskId(progress,state,task.getTaskId());
            }
        }else{
            regionalTaskRepository.updaxzxtatusByTaskId(progress,state,task.getTaskId());
        }
    }

}
