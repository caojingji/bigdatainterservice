package com.founder.interservice.regionalanalysis.service.impl;

import com.founder.interservice.regionalanalysis.mapper.RegionalTaskMapper;
import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTask;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import com.founder.interservice.regionalanalysis.repository.RegionalRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskRepository;
import com.founder.interservice.regionalanalysis.repository.RegionalTaskResultRepository;
import com.founder.interservice.regionalanalysis.service.RegionalAnalysisService;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

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
    public int quertRegionalCountByTaskId(String taskId) throws Exception {
        return regionalTaskMapper.quertRegionalCountByTaskId(taskId);
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
    /**
    *
    * @Description: 使用jpa分页查询显示任务结果
    * @Param:
        * @param param 封装了查询条件
    * @return:
    * @Author: cao peng
    * @date: 2018/8/30 0030-13:06
    */
    @Override
    public Page<RegionalTaskResult> findRegionalTaskResult(Integer page,Integer size,RegionalTaskResult param) throws Exception {
        Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC,"djsj");
        Page<RegionalTaskResult> resultPage = taskResultRepository.findAll(new Specification<RegionalTaskResult>() {
            @Override
            public Predicate toPredicate(Root<RegionalTaskResult> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtil.ckeckEmpty(param.getTaskId())) {
                    list.add(criteriaBuilder.equal(root.get("taskId").as(String.class),param.getTaskId()));
                }
                if(!StringUtil.ckeckEmpty(param.getObjectType())){
                    if("01".equals(param.getObjectType())){
                        list.add(criteriaBuilder.equal(root.get("objectType").as(String.class),param.getObjectType()));
                    }else if("02".equals(param.getObjectType())){
                        Expression<String> exp = root.<String>get("objectType");
                        list.add(exp.in(Arrays.asList("6424","6422","6423","7888"))); //汽车蓝色号码、汽车黄色号码、汽车白色号码，摩托车黄色号码
                    }
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return resultPage;
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
