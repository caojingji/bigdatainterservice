package com.founder.interservice.caseinformation.service.impl;

import com.founder.interservice.loginlog.model.LoginLog;
import com.founder.interservice.mapper.gxzxt.CaseInformationMapper;
import com.founder.interservice.caseinformation.model.CaseInformation;
import com.founder.interservice.caseinformation.service.CaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseInformationServiceImpl implements CaseInformationService {

    @Autowired
    CaseInformationMapper caseInformationMapper;

    @Override
    public List<CaseInformation> selectCaseInformationList(String sfzh){
        List<CaseInformation> caseInformation = caseInformationMapper.selectCaseInformationList(sfzh);


        return caseInformation;
    }
}
