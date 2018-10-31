package com.founder.interservice.caseinformation.service;

import java.util.List;

public interface CaseInformationService {

    /**
     * 根据身份证号搜索案件信息
     * @param sfzh
     * @return
     */
    public List<Object> selectCaseInformationList(String sfzh);
}
