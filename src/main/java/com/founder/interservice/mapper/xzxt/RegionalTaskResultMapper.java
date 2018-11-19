package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.regionalanalysis.model.QueryRegionalTaskResult;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName： RegionalTaskResultMapper
 * @Auther： weiyuanzuo
 * @Description: java类作用描述
 * @CreateDate： 2018-11-16
 * @Version: 1.0
 */
@Repository
public interface RegionalTaskResultMapper {

    public List<RegionalTaskResult> findRegionalTaskResultList(QueryRegionalTaskResult param) throws Exception;
    public int findRegionalTaskResultListTotalCount(QueryRegionalTaskResult param) throws Exception;
}
