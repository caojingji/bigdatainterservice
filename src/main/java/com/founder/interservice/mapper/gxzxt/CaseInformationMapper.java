package com.founder.interservice.mapper.gxzxt;

import com.founder.interservice.caseinformation.model.CaseInformation;
import com.founder.interservice.loginlog.model.LoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseInformationMapper {

    /**
     * 使用身份证号查询案件信息
     * @param sfzh
     * @return
     */
    public List<CaseInformation> selectCaseInformationList(String sfzh);
}
