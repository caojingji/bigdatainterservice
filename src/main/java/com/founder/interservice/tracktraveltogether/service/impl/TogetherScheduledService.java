package com.founder.interservice.tracktraveltogether.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import com.founder.interservice.tracktraveltogether.repository.TogetherTaskResultRepository;
import com.founder.interservice.tracktraveltogether.repository.TrackTogetherTaskRepository;
import com.founder.interservice.util.HttpUtil;
import com.founder.interservice.util.KeyUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @ClassName： TogetherScheduledService
 * @Auther： 曹鹏
 * @Description: 定时获取任务状态和任务结果的定时类
 * @CreateDate： 2018-08-22 16:46
 * @Version: 1.0
 */
@Component
@Async
public class TogetherScheduledService {

    @Value(value = "${wabigdata.trackTravelTogetherForPhoneTaskStatus.url}")
    private String TOGETHER_STATUS_URL; //获取任务状态
    @Value(value = "${wabigdata.trackTravelTogetherForPhoneTaskInfo.url}")
    private String TOGETHER_INFO_URL; //获取任务结果
    @Autowired
    private TrackTogetherTaskRepository taskRepository;
    @Autowired
    private TogetherTaskResultRepository taskResultRepository;
    /**
     *
     * @Description: 查取任务结果的定时方法
     * @Param:

     * @return:
     * @Author: cao peng
     * @date: 2018/8/22 0022-16:35
     */
    @Scheduled(initialDelay = 10000,fixedDelay = 180000) //项目启动后延迟10秒执行，每次执行完后三分钟后再次执行
    public void queryTaskResult(){
        System.out.println("=============伴随定时任务开始执行================");
        try{
            //1 下去查询任务表中 progress = 0  and status = QUEUEING的任务
            TrackTogetherTask togetherTask = new TrackTogetherTask();
            togetherTask.setState("QUEUEING");
            togetherTask.setProgress("0");
            Example<TrackTogetherTask> taskExample = Example.of(togetherTask);
            List<TrackTogetherTask> taskList = taskRepository.findAll(taskExample);
            if(taskList != null && !taskList.isEmpty()){
                for (TrackTogetherTask task:taskList) {
                    String status_url = TOGETHER_STATUS_URL + "&taskId="+task.getTaskId();
                    //String statusStr = HttpUtil.doGet(status_url);
                    String statusStr = "{\"progress\":0.8,\"state\":\"TIMEOUT\"}";
                    System.out.println("statusStr ======================== " + statusStr);
                    if(statusStr != null && !statusStr.isEmpty() && statusStr.startsWith("{") && statusStr.endsWith("}")){
                        JSONObject jsonObject = JSONObject.parseObject(statusStr);
                        String progress = jsonObject.getString("progress");
                        String state = jsonObject.getString("state");
                        String info_url = TOGETHER_INFO_URL + "&taskId=" + task.getTaskId();
                        //String taskInfoResult = HttpUtil.doGet(info_url);
                        String taskInfoResult = "{\"items\":[{\"count\":75,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460013088311061\"},{\"count\":70,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460029233464484\"},{\"count\":65,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460013022609934\"},{\"count\":63,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460013312607010\"},{\"count\":57,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460018669002987\"},{\"count\":53,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460008397079525\"},{\"count\":53,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460020523597601\"},{\"count\":53,\"objectType\":4394,\"objectTypeName\":\"电话号码\",\"objectValue\":\"460003164872839\"}],\"taskId\":\"98f6bcd4621d373cade4e832627b4f6-540-xzxt-api-3-1536231954767\"}";
                        while(StringUtil.ckeckEmpty(taskInfoResult) || !taskInfoResult.startsWith("{")){
                            taskInfoResult = HttpUtil.doGet(info_url);
                        }
                        getAndSaveInfo(taskInfoResult,task,progress,state);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getAndSaveInfo(String taskInfoResult,TrackTogetherTask task,String progress,String state) throws  Exception{
        JSONObject o = JSONObject.parseObject(taskInfoResult);
        JSONArray jsonArray = o.getJSONArray("items");
        if(jsonArray != null && jsonArray.size() > 0){
            List<TogetherTaskResult> taskResults = jsonArray.toJavaList(TogetherTaskResult.class);
            if(taskResults != null && !taskResults.isEmpty()){
                for (TogetherTaskResult r:taskResults) {
                    r.setTaskId(task.getTaskId());
                    r.setXXZJBH(KeyUtil.getUniqueKey("TT"));
                    r.setDjsj(new Date());
                }
                TogetherTaskResult param = new TogetherTaskResult();
                param.setTaskId(task.getTaskId());
                Example<TogetherTaskResult> example = Example.of(param);
                List<TogetherTaskResult> results = taskResultRepository.findAll(example);
                if(results == null || results.isEmpty()){
                    taskResultRepository.save(taskResults);
                }
                taskRepository.updaxzxtatusByTaskId(progress,state,task.getTaskId());
            }
        }else{
            taskRepository.updaxzxtatusByTaskId(progress,state,task.getTaskId());
        }
    }

}
