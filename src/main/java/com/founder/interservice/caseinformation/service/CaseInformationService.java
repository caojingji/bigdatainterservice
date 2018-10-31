package com.founder.interservice.caseinformation.service;

import com.founder.interservice.caseinformation.model.CaseInformation;

import java.util.List;

public interface CaseInformationService {

    /**
     * 根据身份证号搜索案件信息
     * @param sfzh
     * @return
     */
    public List<CaseInformation> selectCaseInformationList(String sfzh);
}
