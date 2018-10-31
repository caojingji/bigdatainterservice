package com.founder.interservice.caseinformation.service.impl;

import com.founder.interservice.caseinformation.mapper.CaseInformationMapper;
import com.founder.interservice.caseinformation.service.CaseInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseInformationServiceImpl implements CaseInformationService {

    @Autowired
    CaseInformationMapper caseInformationMapper;

    @Override
    public List<Object> selectCaseInformationList(String sfzh){
        List<Object> caseInformation = caseInformationMapper.selectCaseInformationList(sfzh);


        return caseInformation;
    }
}
