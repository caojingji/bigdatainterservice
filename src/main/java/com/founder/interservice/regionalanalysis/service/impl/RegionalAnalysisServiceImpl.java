package com.founder.interservice.regionalanalysis.service.impl;

import com.founder.interservice.regionalanalysis.mapper.RegionalTaskMapper;
import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.regionalanalysis.repository.RegionalRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskResultRepository;
import com.founder.interservice.regionalanalysis.service.RegionalAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RegionalTaskResultRepository taskResultRepository;
    @Autowired
    private RegionalTaskMapper regionalTaskMapper;

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

    @Override
    public List<RegionalTaskResult> getTaskResults(RegionalTaskResult param) throws Exception{
        Example<RegionalTaskResult> example = Example.of(param);
        return taskResultRepository.findAll(example);
    }

    /**
    *
    * @Description: 根据案事件编号查询任务该案事件下的任务
    * @Param:
        * @param
    * @return: java.util.List<com.founder.interservice.regionalanalysis.model.RegionalTask>
    * @Author: cao peng
    * @date: 2018/8/25 0025-14:22
    */
    @Override
    public Map<String,Object> queryTasksByAsjbh(RegionalTask param) throws Exception {
        List<RegionalTask> regionalTasks = regionalTaskMapper.findTaskListByAsjbh(param);
        int total = regionalTaskMapper.findTaskListByAsjbhTotalCount(param);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("regionalTasks", regionalTasks);
        return resultMap;
    }
}
