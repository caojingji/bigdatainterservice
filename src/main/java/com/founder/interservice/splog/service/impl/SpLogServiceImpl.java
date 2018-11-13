package com.founder.interservice.splog.service.impl;

import com.founder.interservice.exception.InterServiceException;
import com.founder.interservice.splog.model.SpLog;
import com.founder.interservice.splog.repository.SpLogRepository;
import com.founder.interservice.splog.service.SpLogService;
import com.founder.interservice.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class SpLogServiceImpl implements SpLogService {

    @Autowired
    private SpLogRepository spLogRepository;
    @Override
    public void saveSpLog(SpLog spLog) throws Exception {
        try{
            spLogRepository.save(spLog);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询审批日志
     * @param spLog
     * @return
     * @throws InterServiceException
     */
    @Override
    public Page<SpLog> querySpLogs(SpLog spLog) throws InterServiceException {
        Pageable pageable = new PageRequest(spLog.getPage(),spLog.getSize(), Sort.Direction.DESC,"djsj");
        Page<SpLog> resultPage = spLogRepository.findAll(new Specification<SpLog>(){
            @Override
            public Predicate toPredicate(Root<SpLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(null != spLog){
                    predicate = getPredicate(predicate,root,cb,spLog);
                }
                return predicate;
            }
        },pageable);
        return resultPage;
    }

    private Predicate getPredicate(Predicate predicate,Root<SpLog> root,CriteriaBuilder cb,SpLog spLog){
        if (!StringUtil.ckeckEmpty(spLog.getAsjbh())) {
            predicate.getExpressions().add(cb.like(root.get("asjbh"), "%"+spLog.getAsjbh()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getSpTitle())){
            predicate.getExpressions().add(cb.like(root.get("spTitle"), "%"+spLog.getSpTitle()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getSpbsh())){
            predicate.getExpressions().add(cb.like(root.get("spbsh"), "%"+spLog.getSpbsh()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getCqrJh())){
            predicate.getExpressions().add(cb.like(root.get("cqrJh"), "%"+spLog.getCqrJh()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getCqrXm())){
            predicate.getExpressions().add(cb.like(root.get("cqrXm"), "%"+spLog.getCqrXm()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getCqrSfzh())){
            predicate.getExpressions().add(cb.like(root.get("cqrSfzh"), "%"+spLog.getCqrSfzh()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getCqrLxdh())){
            predicate.getExpressions().add(cb.like(root.get("cqrLxdh"), "%"+spLog.getCqrLxdh()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getDldwmc())){
            predicate.getExpressions().add(cb.like(root.get("dldwmc"), "%"+spLog.getDldwmc()+"%"));
        }
        if(!StringUtil.ckeckEmpty(spLog.getSpzt())){
            predicate.getExpressions().add(cb.equal(root.get("spzt"), spLog.getSpzt()));
        }
        if(!StringUtil.ckeckEmpty(spLog.getBshlxmc())){
            predicate.getExpressions().add(cb.equal(root.get("bshlxdm"), spLog.getBshlxmc()));
        }
        if(null != spLog.getStartTime()){
            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("djsj").as(Date.class), spLog.getStartTime()));
        }
        if(null != spLog.getEndTime()){
            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("djsj").as(Date.class), spLog.getEndTime()));
        }
        return predicate;
    }
}
