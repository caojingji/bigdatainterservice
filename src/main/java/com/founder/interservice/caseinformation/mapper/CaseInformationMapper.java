package com.founder.interservice.caseinformation.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseInformationMapper {

    /**
     * 使用身份证号查询案件信息
     * @param sfzh
     * @return
     */
    public List<Object> selectCaseInformationList(String sfzh);
}
