package com.founder.interservice.regionalanalysis.service;

import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;

import java.util.List;

/**
 * @ClassName： RegionalAnalysisService
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 11:25
 * @Version: 1.0
 */
public interface RegionalAnalysisService {
    public void saveRegional(Regional regional) throws Exception;
    public void saveRegionalList(List<Regional> regionals) throws Exception;

    public void saveRegionalTask(RegionalTask regionalTask) throws Exception;
    public void saveRegionalTaskList(List<RegionalTask> regionalTasks) throws Exception;

}
