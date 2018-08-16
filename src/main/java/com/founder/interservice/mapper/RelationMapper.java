package com.founder.interservice.mapper;

import com.founder.interservice.model.Relation;
import com.founder.interservice.querymodel.RelationFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {
    /**
     * 插入
     * @param relation
     * @return
     */
    public String insertRelation(Relation relation) throws Exception;

    /**
     * 批量插入
     * @param relations
     * @throws Exception
     */
    public void insertRelations(List<Relation> relations) throws Exception;

    /**
     * 查询
     * @param relationFilter
     * @return
     * @throws Exception
     */
    public List<Relation> getRelations(RelationFilter relationFilter) throws Exception;
}
