package com.founder.interservice.regionalanalysis.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.regionalanalysis.repository.RegionalRepository;
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

import java.util.Arrays;
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
    private RegionalRepository regionalRepository;
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
    @Scheduled(cron = "0 0/1 * * * ?") //每隔五分钟执行一次
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
                    String statusStr = HttpUtil.doGet(status_url);
                    //String statusStr = "{\"progress\":1,\"state\":\"FINISHED\"}";
                    if(statusStr != null && !statusStr.isEmpty()){
                        JSONObject jsonObject = JSONObject.parseObject(statusStr);
                        int progress = jsonObject.getIntValue("progress");
                        String state = jsonObject.getString("state");
                        if(progress == 1 && "FINISHED".equals(state)){ //任务执行完成  这时需要去取回任务结果值
                            String info_url = REGIONAL_ANALYSIS_TASK_INFO + "taskId=" + task.getTaskId();
                            String taskInfoResult = HttpUtil.doGet(info_url);
                            //String taskInfoResult = "{\"results\":[{\"objectType\":6424,\"objectTypeName\":\"汽车蓝色号牌\",\"objectValue\":\"渝B7T762\"},{\"objectType\":4314,\"objectTypeName\":\"IMSI\",\"objectValue\":\"460092380008864\"},{\"objectType\":4329,\"objectTypeName\":\"MAC地址\",\"objectValue\":\"DAA119018598\"}],\"status\":\"ok\"}";
                            if(taskInfoResult!= null && !taskInfoResult.isEmpty()){
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
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
