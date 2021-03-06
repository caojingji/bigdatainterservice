package com.founder.interservice.tracktraveltogether.service.impl;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.enums.ResultEnum;
import com.founder.interservice.mapper.xzxt.TogetherTaskResultMapper;
import com.founder.interservice.mapper.xzxt.TrackTogetherMapper;
import com.founder.interservice.tracktraveltogether.model.TogetherTaskResult;
import com.founder.interservice.tracktraveltogether.model.TrackTogetherTask;
import com.founder.interservice.tracktraveltogether.repository.TogetherTaskResultRepository;
import com.founder.interservice.tracktraveltogether.repository.TrackTogetherTaskRepository;
import com.founder.interservice.tracktraveltogether.service.TrackTogetherService;
import com.founder.interservice.util.HttpUtil;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName： TrackTogetherServiceImpl
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-09-09 20:22
 * @Version: 1.0
 */
@Transactional
@Service
public class TrackTogetherServiceImpl implements TrackTogetherService {

    @Value(value = "${wabigdata.trackTravelTogetherForPhoneTask.url}")
    private String TOGETHER_TASK_URL;

    @Autowired
    private TrackTogetherTaskRepository taskRepository;
    @Autowired
    private TogetherTaskResultRepository taskResultRepository;
    @Autowired
    private TrackTogetherMapper trackTogetherMapper;
    @Autowired
    private TogetherTaskResultMapper togetherTaskResultMapper;

    /**
     *
     * @Description: 发送伴随任务 并且返回任务编号
     * @Param:
     * @param trackParam 任务所需参数
     * @return: java.lang.String
     * @Author: cao peng
     * @date: 2018/9/9 0009-20:55
     */
    @Override
    public String sendTrackTogetherTask(TrackTogetherTask trackParam){
        try{
            String taskId = null;
            if(trackParam != null){
                String url = TOGETHER_TASK_URL + "&objectType=" + trackParam.getObjectType()
                        + "&objectValue=" + trackParam.getImsi()
                        + "&taskName="+trackParam.getTaskName()
                        + "&taskCaseId=" + trackParam.getTaskCaseId()
                        + "&startTime=" + trackParam.getStartTime().getTime()
                        + "&endTime=" + trackParam.getEndTime().getTime();
                System.out.println(" 伴随------发送任务URL =================" + url);
                taskId = HttpUtil.doGet(url);
                //taskId = "1231231231";
                System.out.println("taskId ===============" +taskId);
            }
            return taskId;
        }catch (Exception e){
            e.printStackTrace();
            throw new InterServiceException(ResultEnum.REQUEST_URL_ERROR);
        }
    }
    /**
     *
     * @Description: 保存任务
     * @Param:
     * @param trackParam 参数
     * @return: void
     * @Author: cao peng
     * @date: 2018/9/9 0009-21:15
     */
    @Override
    public void saveTogetherTask(TrackTogetherTask trackParam){
        try{
            taskRepository.save(trackParam);
        }catch (Exception e){
            throw new InterServiceException(ResultEnum.RESULT_ERROR);
        }
    }
    /**
     *
     * @Description: 根据任务编号查询伴随结果
     * @Param:
     * @param taskId 任务编号
     * @return: java.util.List<com.founder.interservice.tracktraveltogether.model.TogetherTaskResult>
     * @Author: cao peng
     * @date: 2018/9/11 0011-21:23
     */
    @Override
    public Page<TogetherTaskResult> findTogetherTaskResult(Integer page, Integer size,String taskId) throws InterServiceException{
        try{
            TogetherTaskResult param = new TogetherTaskResult();
            param.setTaskId(taskId);
            //return taskResultRepository.findAllByTaskId(taskId);
            Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC,"djsj");
            Page<TogetherTaskResult> resultPage = taskResultRepository.findAll(new Specification<TogetherTaskResult>() {
                @Override
                public Predicate toPredicate(Root<TogetherTaskResult> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list = new ArrayList<>();
                    if (!StringUtil.ckeckEmpty(taskId)) {
                        list.add(criteriaBuilder.equal(root.get("taskId").as(String.class),taskId));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
            },pageable);
            return resultPage;
        }catch (Exception e){
            throw new InterServiceException(ResultEnum.RESULT_ERROR);
        }
    }
    /**
     *
     * @Description: 根据任务编号查看任务进行状态
     * @Param:
     * @param taskId 任务编号
     * @return: com.founder.interservice.tracktraveltogether.model.TrackTogetherTask
     * @Author: cao peng
     * @date: 2018/9/17 0017-10:12
     */
    @Override
    public TrackTogetherTask findByTaskId(String taskId) throws InterServiceException {
        try {
            return taskRepository.findAllByTaskId(taskId);
        }catch (Exception e){
            e.printStackTrace();
            throw new InterServiceException(ResultEnum.RESULT_ERROR);
        }
    }

    /**
     * 根据案事件编号和服务标识号查询伴随任务列表
     * @param taskParam
     * @return
     */
    @Override
    public List<TrackTogetherTask> queryTasksByAsjbhAndFwbsh(TrackTogetherTask taskParam) {
        return trackTogetherMapper.queryTasksByAsjbhAndFwbsh(taskParam);
    }

    @Override
    public List<TogetherTaskResult> getTogetherTaskResultList(TogetherTaskResult taskParam) {
        return togetherTaskResultMapper.getTogetherTaskResultList(taskParam);
    }

    @Override
    public int getTogetherTaskResultListTotalCount(TogetherTaskResult taskParam) {
        return togetherTaskResultMapper.getTogetherTaskResultListTotalCount(taskParam);
    }
}
