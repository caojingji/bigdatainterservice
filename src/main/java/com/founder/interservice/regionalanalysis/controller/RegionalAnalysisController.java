package com.founder.interservice.regionalanalysis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.service.RegionalAnalysisService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.HttpUtil;
import com.founder.interservice.util.KeyUtil;
import com.founder.interservice.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName： RegionalAnalysisController
 * @Auther： 曹鹏
 * @Description: 区域碰撞controller
 * @CreateDate： 2018-08-21 18:57
 * @Version: 1.0
 */
@RestController
@CrossOrigin  //跨域访问
public class RegionalAnalysisController {

    @Autowired
    private RegionalAnalysisService regionalAnalysisService;
    @Value(value = "${wabigdata.regionalAnalysisTask.url}")
    private String REGION_ALANALYSIS_URL; //发送任务接口

    /**
     *
     * @Description: 发送任务接口并将数据入库保存
     * @Param:
     * @param paramStr
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cao peng
     * @date: 2018/8/22 0022-16:34
     */
    @RequestMapping(value = "/sendRegionalAnalysisTask")
    public ModelAndView sendRegionalAnalysisTask(String paramStr){
        try{
            paramStr = "{\"taskName\":\"test\",\"perform\":{\"expression\":\"A1∩A2\",\"regional\":[{\"endTime\":1534867200000,\"lc\":[{\"j\":106.2829,\"w\":29.4457},{\"j\":106.301,\"w\":29.4536},{\"j\":106.3032,\"w\":29.4534},{\"j\":106.3012,\"w\":29.4428},{\"j\":106.2919,\"w\":29.4350}],\"name\":\"A1\",\"source\":[0],\"startTime\":1534262400000},{\"endTime\":1534867200000,\"lc\":[{\"j\":106.2720,\"w\":29.4415},{\"j\":106.2817,\"w\":29.4449},{\"j\":106.2850,\"w\":29.4421},{\"j\":106.2950,\"w\":29.4334},{\"j\":106.2740,\"w\":29.4340}],\"name\":\"A2\",\"source\":[0],\"startTime\":1534262400000}]},\"taskCaseId\":\"65\"}";
            String taskId = "123123131231";//HttpUtil.doPost(REGION_ALANALYSIS_URL,paramStr);
            ModelAndView modelAndView = new ModelAndView();
            if(taskId != null && !taskId.isEmpty()){
                JSONObject jsonObject = JSONObject.parseObject(paramStr);
                RegionalTask regionalTask = new RegionalTask();
                List<Regional> regionalList = null;

                regionalTask.setTaskId(taskId);
                regionalTask.setTaskName(jsonObject.getString("taskName"));
                regionalTask.setTaskCaseId(jsonObject.getString("taskCaseId"));
                regionalTask.setDjsj(new Date());
                regionalTask.setProgress("0");
                regionalTask.setState("QUEUEING");

                if(jsonObject != null){
                    JSONObject jsonObject1 = jsonObject.getJSONObject("perform");
                    if(jsonObject1 != null){
                        regionalTask.setExpression(jsonObject1.getString("expression"));
                        JSONArray jsonArray = jsonObject1.getJSONArray("regional");
                        if(jsonArray != null && !jsonArray.isEmpty()){
                            regionalList = new ArrayList<>();
                            for (int i = 0;i <= jsonArray.size() - 1; i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                if(object != null){
                                    Regional regional = new Regional();
                                    regional.setTaskId(taskId);
                                    regional.setDjsj(new Date());
                                    regional.setRegionalId(KeyUtil.getUniqueKey("RT"));
                                    regional.setName(object.getString("name"));
                                    regional.setLc(object.getJSONArray("lc").toJSONString());
                                    regional.setSource(object.getJSONArray("source").toJSONString());
                                    regional.setStartTime(new Date(object.getLong("startTime")));
                                    regional.setEndTime(new Date(object.getLong("endTime")));
                                    regionalList.add(regional);
                                }
                            }
                        }
                        regionalTask.setRegionals(regionalList);
                    }
                }
                if(regionalTask != null){
                    regionalAnalysisService.saveRegionalTask(regionalTask);
                }
                ResultVO resultVO = ResultVOUtil.success(taskId);
                modelAndView.addObject("resultVo",resultVO);
            }else{
                ResultVO resultVO = ResultVOUtil.error(ResultEnum.TASK_SEND_ERROR.getCode(),ResultEnum.TASK_SEND_ERROR.getMessage());
                modelAndView.addObject("resultVo",resultVO);
            }
            modelAndView.setViewName("task/taskresult");
            return modelAndView;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
