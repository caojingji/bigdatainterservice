package com.founder.interservice.mapper.xzxt;

import com.founder.interservice.recordLog.model.Querylog;
import com.founder.interservice.recordLog.queryModel.QuerylogFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

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


    /**
     * 条件查询
     */
    //（总条数）
    public Integer queryQueryLogCount(QuerylogFilter filter) throws Exception;
    //（分页list）
    public List<Querylog> queryQueryLogByPage(QuerylogFilter filter) throws Exception;

}
