package com.founder.interservice.caseinformation.contorller;

import com.alibaba.fastjson.JSONObject;
import com.founder.interservice.caseinformation.model.CaseInformation;
import com.founder.interservice.caseinformation.service.CaseInformationService;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.regionalanalysis.VO.RegionalTaskResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin  //跨域访问
public class CaseInformationContorller {

    @Autowired
    CaseInformationService caseInformationService;

    /**
     * 根据身份证号搜索案件信息
     * @param sfzh
     * @return
     */
    @RequestMapping(value = "/selectCaseInformation", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JSONObject selectCaseInformationList(@RequestParam(value = "sfzh") String sfzh){
        JSONObject resultObj = new JSONObject();
        List<CaseInformation> caseInformationList = new ArrayList<>();
        try {
            caseInformationList = caseInformationService.selectCaseInformationList(sfzh);
            if(caseInformationList != null  && !caseInformationList.isEmpty() ){
                resultObj.put("caseInformationList", caseInformationList);
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message", ResultEnum.SUCCESS.getMessage());
            }else {
                resultObj.put("caseInformationList", caseInformationList);
                resultObj.put("code", ResultEnum.SUCCESS.getCode());
                resultObj.put("message", "无数据");
            }
        } catch (Exception e) {
            resultObj.put("code", ResultEnum.RESULT_ERROR.getCode());
            resultObj.put("message",ResultEnum.RESULT_ERROR.getMessage());
            e.printStackTrace();
        }
        return resultObj;
    }

}
