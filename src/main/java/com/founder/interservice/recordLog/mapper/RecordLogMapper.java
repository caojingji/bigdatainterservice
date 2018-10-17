package com.founder.interservice.recordLog.mapper;

import com.founder.interservice.recordLog.model.Querylog;
import org.springframework.stereotype.Repository;

/**
 * @ClassName： RegionalTaskMapper
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-10-17
 * @Version: 1.0
 */
@Repository
public interface RecordLogMapper {

    /**
     * 添加
     * @param querylog
     * @return
     */
    public void saveQueryLog(Querylog querylog) throws Exception;
}
