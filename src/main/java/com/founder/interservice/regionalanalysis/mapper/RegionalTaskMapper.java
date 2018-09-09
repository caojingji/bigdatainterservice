package com.founder.interservice.regionalanalysis.mapper;

import com.founder.interservice.regionalanalysis.model.RegionalTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName： RegionalTaskMapper
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-25 15:20
 * @Version: 1.0
 */
@Repository
public interface RegionalTaskMapper {

    public List<RegionalTask> findTaskListByAsjbh(RegionalTask param) throws Exception;
    public int findTaskListByAsjbhTotalCount(RegionalTask param) throws Exception;
    public int quertRegionalCountByTaskId(String taskId) throws Exception;
}
