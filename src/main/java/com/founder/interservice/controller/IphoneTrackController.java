package com.founder.interservice.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.model.ResultObj;
import com.founder.interservice.querymodel.RelationLocalFilter;
import com.founder.interservice.recordLog.model.Querylog;
import com.founder.interservice.recordLog.service.RecordLogService;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.ResultVOUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 手机轨迹controller
 */
@Controller
@CrossOrigin
public class IphoneTrackController {

    @Autowired
    private IphoneTrackService iphoneTrackService;
    //查询日志记录
    @Autowired
    private RecordLogService recordLogService;

    @RequestMapping("/getObjectAllRelation")
    @ResponseBody
    public ResultVO getObjectRelationAll(String objValue,String objType){
        ResultVO resultObj = null;
        try{
            JSONObject jsonObject = iphoneTrackService.getObjectRelationAll(objValue);
            resultObj = ResultVOUtil.success(jsonObject);
        }catch (InterServiceException e){
            e.printStackTrace();
            resultObj = ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return resultObj;
    }

    @RequestMapping("/getObjectRelationAll")
    @ResponseBody
    public ResultObj getObjectRelationAll(String asjbh,String cxrXm,String cxrSfzh,String cxrJh,String cxrLxdh,String type,String objValue){
        ResultObj resultObj = null;
        try{
            resultObj = iphoneTrackService.getObjectRelationAll(objValue,type);
            if (resultObj!=null){
                //查出数据保存入库
                iphoneTrackService.saveObjectRelationLocal(resultObj);
            }
            //添加查询日志
            //添加查询日志记录功能
            Querylog logParam = new Querylog();
            logParam.setCxrXm(cxrXm);
            logParam.setCxrSfzh(cxrSfzh);
            logParam.setCxrJh(cxrJh);
            logParam.setCxrLxdh(cxrLxdh);
            logParam.setAsjbh(asjbh);
            logParam.setCxbsh(objValue);
            logParam.setBshlxdm(type);
            if(!StringUtil.ckeckEmpty(type)){
                switch (type){
                    case "001":logParam.setBshlxmc("手机号码");break;
                    case "002":logParam.setBshlxmc("QQ号码");break;
                    case "003":logParam.setBshlxmc("微信ID");break;
                    case "004":logParam.setBshlxmc("身份证号");break;
                    case "005":logParam.setBshlxmc("车牌号码");break;
                    default:logParam.setBshlxmc("");break;
                }
            }else{
                logParam.setBshlxmc("");
            }
            recordLogService.saveQueryLog(logParam);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultObj;
    }



    /**
     * 通过手机号调取手机轨迹
     * @param objValue 参数值
     * @param objType 参数类型 （001：手机号；002：QQ号；003：微信号）
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @param yhCate 用户类别（00：方正；01：嘉崎；02：新德汇；03：天彦；04：海鑫）
     * @return
     */
    @RequestMapping(value = "/iphoneTrackForSjhm",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResultVO iphoneTrackForSjhm(String objValue, String objType, String kssj, String jssj, String yhCate) {
        ResultVO resultVO = null;
        List<Object> resultArr = new ArrayList<Object>();
        try {
            Map<String,Object> paramMap = checkParam(objValue,objType,kssj,jssj,yhCate);
            int code = (int)paramMap.get("code");
            if(code == 0){
                List<String> imsis = new ArrayList<>();
                JSONObject jsonObject = iphoneTrackService.getObjectRelation(objValue);
                if(jsonObject != null){
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    if(jsonArray != null && jsonArray.size() > 0){
                        for (int i = 0; i <= jsonArray.size() - 1;i++){
                            JSONObject resObj = jsonArray.getJSONObject(i);
                            String objectType = resObj.getString("objectToTypeName");
                            if (objectType != null && "IMSI".equals(objectType)){
                                String imsi = (String) resObj.get("objectToValue");
                                if(!imsis.contains(imsi)){
                                    imsis.add(imsi);
                                }
                            }
                        }
                    }
                }
                if(imsis != null && !imsis.isEmpty()){
                    kssj = kssj.contains(" ") ? DateUtil.convertStringToDateTime(kssj).getTime()+"" : DateUtil.convertStringToDate(kssj).getTime()+"";
                    jssj = jssj.contains(" ") ? DateUtil.convertStringToDateTime(jssj).getTime()+"" : DateUtil.convertStringToDate(jssj).getTime()+"";
                    for (String imsi:imsis) {
                        Map<String,Object> resultMap = iphoneTrackService.iphoneTrackForSjhm(imsi,kssj,jssj);
                        if(resultMap != null){
                            resultArr.add(resultMap);
                        }
                    }
                    if(resultArr != null && resultArr.size() > 0){
                        resultVO = ResultVOUtil.success(resultArr);
                    }else{
                        resultVO = ResultVOUtil.success();
                    }
                }else{
                    resultVO = ResultVOUtil.success();
                }
            }else if(code == 13){
                resultVO = ResultVOUtil.success();
            }else{
                resultVO = ResultVOUtil.error((int)paramMap.get("code"),(String) paramMap.get("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVOUtil.error(ResultEnum.RESULT_ERROR.getCode(),ResultEnum.RESULT_ERROR.getMessage());
        }
        return resultVO;
    }



    /**
     * 检验所传的参数
     *
     * @param objVal 参数值
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    private Map<String, Object> checkParam(String objVal,String objType,String kssj, String jssj,String yhCate) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(yhCate)){
            if(!Arrays.asList("00","01","02","03","04").contains(yhCate)){
                resultMap.put("code", ResultEnum.YHCATE_PARAM_ERROR.getCode());
                resultMap.put("msg", ResultEnum.YHCATE_PARAM_ERROR.getMessage());
            }else{
                if(!StringUtils.isEmpty(objType)){
                    if (!StringUtils.isEmpty(objVal)) {
                        if("001".equals(objType)){
                            resultMap = StringUtil.isPhone(objVal);
                            if (0 == (int)resultMap.get("code")) {
                                resultMap = checkTime(kssj,jssj);
                            }
                        }else if("002".equals(objType)){
                            resultMap = StringUtil.isQQ(objVal);
                            if (0 == (int)resultMap.get("code")) {
                                resultMap = checkTime(kssj,jssj);
                            }
                        }else if("003".equals(objType)){ //微信
                            resultMap.put("code", 13);
                            resultMap.put("msg", "微信");
                        }else{
                            resultMap.put("code", ResultEnum.OBJTYPE_PARAM_ERROR.getCode());
                            resultMap.put("msg", ResultEnum.OBJTYPE_PARAM_ERROR.getMessage());
                        }
                    } else {
                        resultMap.put("code", ResultEnum.PARAM_NOTNULL.getCode());
                        resultMap.put("msg", ResultEnum.PARAM_NOTNULL.getMessage());
                    }
                }else{
                    resultMap.put("code", ResultEnum.OBJTYPE_PARAM_NOTNULL.getCode());
                    resultMap.put("msg", ResultEnum.OBJTYPE_PARAM_NOTNULL.getMessage());
                }
            }
        }else{
            resultMap.put("code", ResultEnum.YHCATE_PARAM_NOTNULL.getCode());
            resultMap.put("msg", ResultEnum.YHCATE_PARAM_NOTNULL.getMessage());
        }
        return resultMap;
    }
    private Map<String,Object> checkTime(String kssj,String jssj){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if (!StringUtils.isEmpty(kssj) && !StringUtils.isEmpty(jssj)) {
            Date kDate = kssj.contains(" ") ? DateUtil.convertStringToDateMinute(kssj) : DateUtil.convertStringToDate(kssj);
            Date jDate = jssj.contains(" ") ? DateUtil.convertStringToDateMinute(jssj) : DateUtil.convertStringToDate(jssj);
            if(kDate == null || jDate == null){
                resultMap.put("code", ResultEnum.TIME_PARAM_ERROR.getCode());
                resultMap.put("msg", ResultEnum.TIME_PARAM_ERROR.getMessage());
            }else{
                int i = DateUtil.compareDate(jDate, kDate);
                if (i < 0) {
                    resultMap.put("code", ResultEnum.TIME_APRAM_ERROR2.getCode());
                    resultMap.put("msg", ResultEnum.TIME_APRAM_ERROR2.getMessage());
                }else{
                    resultMap.put("code", ResultEnum.SUCCESS.getCode());
                    resultMap.put("msg", ResultEnum.SUCCESS.getMessage());
                }
            }
        } else {
            resultMap.put("code", ResultEnum.TIME_PARAM_NOTNULL.getCode());
            resultMap.put("msg", ResultEnum.TIME_PARAM_NOTNULL.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/queryObjectRelationLocal",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean queryObjectRelationLocal(RelationLocalFilter filter){
        boolean yesOrNo = false;
        try{
            yesOrNo = iphoneTrackService.queryObjectRelationLocal(filter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  yesOrNo;
    }
}
