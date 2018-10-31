package com.founder.interservice.caseinformation.contorller;

import com.founder.interservice.caseinformation.model.CaseInformation;
import com.founder.interservice.caseinformation.service.CaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class CaseInformationContorller {

    @Autowired
    CaseInformationService caseInformationService;

    /**
     * 根据身份证号搜索案件信息
     * @param sfzh
     * @return
     */
    @RequestMapping(value = "/selectCaseInformation", method = {RequestMethod.GET,RequestMethod.POST})
    public List<CaseInformation> selectCaseInformationList(@RequestParam(value = "sfzh") String sfzh){

        List<CaseInformation> caseInformationList = caseInformationService.selectCaseInformationList(sfzh);

        for(CaseInformation caseInformation : caseInformationList){
            System.out.println(caseInformation.getAsjbh());
            System.out.println(caseInformation.getAjmc());
            System.out.println(caseInformation.getAsjfsddDzmc());
            System.out.println(caseInformation.getAsjfssjAsjfskssj());
            System.out.println(caseInformation.getJyaq());
        }

        return caseInformationList;
    }
}
