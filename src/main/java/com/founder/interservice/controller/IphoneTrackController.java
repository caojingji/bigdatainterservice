package com.founder.interservice.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.VO.ResultVO;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.model.Track;
import com.founder.interservice.service.IphoneTrackService;
import com.founder.interservice.util.DateUtil;
import com.founder.interservice.util.ResultVOUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * 手机轨迹controller
 */
@RestController
public class IphoneTrackController {

    @Autowired
    private IphoneTrackService iphoneTrackService;

    /**
     * 通过手机号调取手机轨迹
     * @param objValue 参数值
     * @param objType 参数类型 （00：手机号；01：QQ号；02：微信号）
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @param yhCate 用户类别（00：方正；01：嘉崎；02：新德汇；03：天彦；04：海鑫）
     * @return
     */
    @RequestMapping("/iphoneTrackForSjhm")
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
                    for (String imsi:imsis) {
                        kssj = kssj.contains(" ") ? DateUtil.convertStringToDateMinute(kssj).getTime()+"" : DateUtil.convertStringToDate(kssj).getTime()+"";
                        jssj = jssj.contains(" ") ? DateUtil.convertStringToDateMinute(jssj).getTime()+"" : DateUtil.convertStringToDate(jssj).getTime()+"";
                        Map<String,Object> resultMap = iphoneTrackService.iphoneTrackForSjhm(imsi,kssj,jssj);
                        resultArr.add(resultMap);
                    }
                    resultVO = ResultVOUtil.success(resultArr);
                }else{
                    resultVO = ResultVOUtil.success();
                }
            }else{
                resultVO = ResultVOUtil.error((int)paramMap.get("code"),(String) paramMap.get("msg"));
            }
        }catch (Exception e){
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
            if(!StringUtils.isEmpty(objType)){
                if (!StringUtils.isEmpty(objVal)) {
                    if("00".equals(objType)){
                        resultMap = StringUtil.isPhone(objVal);
                        if (0 == (int)resultMap.get("code")) {
                            resultMap = checkTime(kssj,jssj);
                        }
                    }else if("01".equals(objType)){
                        resultMap = StringUtil.isQQ(objVal);
                        if (0 == (int)resultMap.get("code")) {
                            resultMap = checkTime(kssj,jssj);
                        }
                    }else if("02".equals(objType)){ //微信

                    }
                } else {
                    resultMap.put("code", ResultEnum.PARAM_NOTNULL.getCode());
                    resultMap.put("msg", ResultEnum.PARAM_NOTNULL.getMessage());
                }
            }else{
                resultMap.put("code", ResultEnum.OBJTYPE_PARAM_ERROR.getCode());
                resultMap.put("msg", ResultEnum.OBJTYPE_PARAM_ERROR.getMessage());
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
}
