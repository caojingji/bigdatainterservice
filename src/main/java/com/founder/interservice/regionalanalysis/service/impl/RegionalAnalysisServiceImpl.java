package com.founder.interservice.regionalanalysis.service.impl;

import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.repository.RegionalRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskRepository;
import com.founder.interservice.regionalanalysis.service.RegionalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName： RegionalAnalysisServiceImpl
 * @Auther： 曹鹏
 * @Description: 区域碰撞service实现类
 * @CreateDate： 2018-08-21 18:58
 * @Version: 1.0
 */
@Transactional
@Service
public class RegionalAnalysisServiceImpl implements RegionalAnalysisService {

    @Autowired
    private RegionalRepository regionalRepository;
    @Autowired
    private RegionalTaskRepository regionalTaskRepository;

    @Override
    public void saveRegional(Regional regional) throws Exception {
        regionalRepository.save(regional);
    }

    @Override
    public void saveRegionalList(List<Regional> regionals) throws Exception {
        regionalRepository.save(regionals);
    }

    @Override
    public void saveRegionalTask(RegionalTask regionalTask) throws Exception {
        List<Regional> regionals = regionalTask.getRegionals();
        if(regionals != null && !regionals.isEmpty()){
            saveRegionalList(regionals);
        }
        regionalTaskRepository.save(regionalTask);
    }

    @Override
    public void saveRegionalTaskList(List<RegionalTask> regionalTasks) throws Exception {
        regionalTaskRepository.save(regionalTasks);
    }
}
