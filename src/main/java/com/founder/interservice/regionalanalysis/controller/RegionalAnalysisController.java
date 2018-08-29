package com.founder.interservice.regionalanalysis.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.model.AutoTbStRy;
import com.founder.interservice.model.ResultObj;
import com.founder.interservice.regionalanalysis.VO.RegionalTaskResultVO;
import com.founder.interservice.regionalanalysis.VO.RegionalTaskVO;
import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.regionalanalysis.service.RegionalAnalysisService;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @ClassName： RegionalAnalysisController
 * @Auther： 曹鹏
 * @Description: 区域碰撞controller
 * @CreateDate： 2018-08-21 18:57
 * @Version: 1.0
 */
@Controller
@CrossOrigin  //跨域访问
public class RegionalAnalysisController {

    @Autowired
    private RegionalAnalysisService regionalAnalysisService;
    @Value(value = "${wabigdata.regionalAnalysisTask.url}")
    private String REGION_ALANALYSIS_URL; //发送任务接口
    @Autowired
    private IphoneTrackService iphoneTrackService; //调取网安数据接口

    @RequestMapping(value = "/toParamJsp")
    public String toParamJsp(){
        return "taskParam";
    }
    @RequestMapping("/toTaskListJsp")
    public  String toTaskListJsp(){
        return "qypz/skgjpztask";
    }
    /**
     *
     * @Description: 根据案事件编号查询其下的任务列表
     * @Param:
     * @param asjbh 案事件编号
     * @return: java.util.List<com.founder.interservice.regionalanalysis.VO.RegionalTaskVO>
     * @Author: cao peng
     * @date: 2018/8/25 0025-15:55
     */
    @RequestMapping("/queryTasksByAsjbh")
    @ResponseBody
    public Map<String,Object> queryTasksByAsjbh(String asjbh,
                                                @RequestParam(value = "page",defaultValue = "0") int page,
                                                @RequestParam(value = "rows",defaultValue = "0") int rows){
        Map<String, Object> resultMap = new HashMap<>();
        List<RegionalTaskVO> regionalTaskVOS = null;
        try {
            EasyUIPage easyUIPage = new EasyUIPage();
            easyUIPage.setPage(page);
            easyUIPage.setPagePara(rows);
            int begin = easyUIPage.getBegin();
            int end = easyUIPage.getEnd();
            RegionalTask param = new RegionalTask();
            param.setTaskCaseId(asjbh);
            param.setStartNum(begin);
            param.setEndNum(end);
            Map<String,Object> regionalMap = regionalAnalysisService.queryTasksByAsjbh(param);
            int total = (int)regionalMap.get("total");
            List<RegionalTask> regionalTasks = (List<RegionalTask>)regionalMap.get("regionalTasks");
            if(regionalTasks != null && regionalTasks.size() > 0){
                regionalTaskVOS = new ArrayList<>();
                for (RegionalTask task:regionalTasks) {
                    RegionalTaskVO taskVO = new RegionalTaskVO();
                    BeanUtils.copyProperties(task,taskVO);
                    regionalTaskVOS.add(taskVO);
                }
            }
            resultMap.put("total", total);
            resultMap.put("rows",regionalTaskVOS);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("total", 0);
            resultMap.put("rows", new ArrayList<>());
        }
        return resultMap;
    }

    @RequestMapping(value = "/getTaskResults",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ModelAndView getTaskResults(RegionalTaskResult param){
        List<RegionalTaskResult> taskResults = null;
        List<RegionalTaskResultVO> taskResultVOSCph = new ArrayList<>();
        List<RegionalTaskResultVO> taskResultVOS = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("qypz/skgjpzjgzs");
        try {
            taskResults = regionalAnalysisService.getTaskResults(param);
            List<String> cpTypes = Arrays.asList("6424","6422","6423","7888"); //汽车蓝色号码、汽车黄色号码、汽车白色号码，摩托车黄色号码
            if(taskResults != null && !taskResults.isEmpty()){
                for(int i = 0; i <= taskResults.size() - 1; i++){
                    String objectType = taskResults.get(i).getObjectType();
                    RegionalTaskResultVO taskResultVO = new RegionalTaskResultVO();
                    BeanUtils.copyProperties(taskResults.get(i),taskResultVO);
                    if("4314".equals(objectType)){
                        String imsi = taskResults.get(i).getObjectValue();
                        taskResultVO = getRyxxData(imsi,taskResultVO);
                        taskResultVOS.add(taskResultVO);
                    }else if(cpTypes.contains(objectType)){
                        String cphm = taskResults.get(i).getObjectValue();
                        taskResultVO = getJdcData(cphm,taskResultVO);
                        taskResultVOSCph.add(taskResultVO);
                    }
                }
            }
            modelAndView.addObject("taskResultVOS",taskResultVOS);
            modelAndView.addObject("taskResultVOSCph",taskResultVOSCph);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("taskResultVOS",taskResultVOS);
            modelAndView.addObject("taskResultVOSCph",taskResultVOSCph);
        }
        return modelAndView;
    }

    public RegionalTaskResultVO getJdcData(String cphm,RegionalTaskResultVO taskResultVO) throws Exception{
        /*1 通过车牌号调取车辆所有人信息*/
        if(!StringUtil.ckeckEmpty(cphm)){
            //1、 通过车牌号码调取对应的拥有人信息
            JSONObject jsonObject = iphoneTrackService.getObjectRelation(cphm);
            if(jsonObject != null){
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                if(jsonArray != null && jsonArray.size() > 0){
                    for (int j = 0; j <= jsonArray.size() - 1;j++){
                        JSONObject resObj = jsonArray.getJSONObject(j);
                        String relativeType = resObj.getString("relativeType");
                        if (relativeType != null && "4398".equals(relativeType)){ //关系类型为 所有人
                            String zjhm = (String) resObj.get("objectToValue"); //证件号码
                            String zjlxName = (String) resObj.get("objectToTypeName"); //证件类型名称
                            String zjlxCode = (String) resObj.get("objectToType"); //证件类型代码
                            String objectFromValue = resObj.getString("objectFromValue");
                            String objectFromTypeName = resObj.getString("objectFromTypeName");
                            taskResultVO.setObjectValue(objectFromValue);
                            taskResultVO.setObjectTypeName(objectFromTypeName);
                            if(StringUtil.ckeckEmpty(taskResultVO.getZjhm())){
                                taskResultVO.setZjhm(zjhm);
                                taskResultVO.setZjlx(zjlxName);
                                taskResultVO.setZjlxCode(zjlxCode);
                            }else{
                                if(!taskResultVO.getZjlx().equals("1")){ //如果已经存入不是身份证 则进行覆盖更新
                                    if(zjlxCode.equals("1")){
                                        taskResultVO.setZjhm(zjhm);
                                        taskResultVO.setZjlx(zjlxName);
                                        taskResultVO.setZjlxCode(zjlxCode);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if("1".equals(taskResultVO.getZjlxCode())){ //类型为1  为身份证号
            //3 通过身份证号调取二代证信息
            if(!StringUtil.ckeckEmpty(taskResultVO.getZjhm())){
                AutoTbStRy tbStRy = new Qgckzp().getQgckAllxxXml(taskResultVO.getZjhm());
                taskResultVO.setXzzDzmc(tbStRy.getXzzDzmc());//现住址
                taskResultVO.setCsdDzmc(tbStRy.getCsdDzmc());//出生地
                if(tbStRy.getCsrqRqgzxx() != null){
                    taskResultVO.setCsrq(DateUtil.convertDateToChineseString(tbStRy.getCsrqRqgzxx()));//出生日期
                    taskResultVO.setAge(DateUtil.getAge(tbStRy.getCsrqRqgzxx())); //age
                }
                taskResultVO.setName(tbStRy.getXm());//姓名
                taskResultVO.setRyzp(tbStRy.getEdzzplj());//人员照片
            }
        }
        return taskResultVO;
    }


    public RegionalTaskResultVO getRyxxData(String imsi,RegionalTaskResultVO taskResultVO) throws Exception{
        if(!StringUtil.ckeckEmpty(imsi)){
            //1、 通过身份证号调取对应的电话号码
            JSONObject jsonObject = iphoneTrackService.getObjectRelation(imsi);
            if(jsonObject != null){
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                if(jsonArray != null && jsonArray.size() > 0){
                    for (int j = 0; j <= jsonArray.size() - 1;j++){
                        JSONObject resObj = jsonArray.getJSONObject(j);
                        String relativeType = resObj.getString("relativeType");
                        if (relativeType != null && "4402".equals(relativeType)){
                            String sjhm = (String) resObj.get("objectToValue");
                            taskResultVO.setSjhm(sjhm);
                            break;
                        }
                    }
                }
            }
        }
        //2 通过手机号码调取身份证号
        if(!StringUtil.ckeckEmpty(taskResultVO.getSjhm())){
            ResultObj resultObj = iphoneTrackService.getObjectRelationAll(taskResultVO.getSjhm(),"00");
            if(resultObj != null && "1".equals(resultObj.getObjType())){
                String zjhm = resultObj.getObjValue();
                taskResultVO.setZjhm(zjhm);
            }
        }
        //3 通过身份证号调取二代证信息
        if(!StringUtil.ckeckEmpty(taskResultVO.getZjhm())){
            AutoTbStRy tbStRy = new Qgckzp().getQgckAllxxXml(taskResultVO.getZjhm());
            taskResultVO.setXzzDzmc(tbStRy.getXzzDzmc());//现住址
            taskResultVO.setCsdDzmc(tbStRy.getCsdDzmc());//出生地
            if(tbStRy.getCsrqRqgzxx() != null){
                taskResultVO.setCsrq(DateUtil.convertDateToChineseString(tbStRy.getCsrqRqgzxx()));//出生日期
                taskResultVO.setAge(DateUtil.getAge(tbStRy.getCsrqRqgzxx())); //age
            }
            taskResultVO.setName(tbStRy.getXm());//姓名
            taskResultVO.setRyzp(tbStRy.getEdzzplj());//人员照片
        }
        return taskResultVO;
    }

    /**
     *
     * @Description: 发送任务接口并将数据入库保存
     * @Param:
     * @param paramStr
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cao peng
     * @date: 2018/8/22 0022-16:34
     */
    @ResponseBody
    @RequestMapping(value = "/sendRegionalAnalysisTask",method = {RequestMethod.GET,RequestMethod.POST})
    public ResultVO sendRegionalAnalysisTask(String paramStr){
        ResultVO resultVO = null;
        try{
            //String taskId = "123123131231";
            String taskId = HttpUtil.doPost(REGION_ALANALYSIS_URL,paramStr);
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
                resultVO = ResultVOUtil.success(taskId);
            }else{
                resultVO = ResultVOUtil.error(ResultEnum.TASK_SEND_ERROR.getCode(),ResultEnum.TASK_SEND_ERROR.getMessage());
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResultEnum.TASK_SEND_ERROR.getCode(),ResultEnum.TASK_SEND_ERROR.getMessage());
        }
        return resultVO;
    }

}
