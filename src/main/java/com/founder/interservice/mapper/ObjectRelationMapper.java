package com.founder.interservice.mapper;

import com.founder.interservice.model.IdcardPhoneCarRalation;
import com.founder.interservice.model.PhoneQQWeChatRalation;
import com.founder.interservice.querymodel.RelationLocalFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRelationMapper {

    //添加IdcardPhoneCarRalation
    public void saveIdRelation(IdcardPhoneCarRalation idRelation) throws Exception;
    //添加PhoneQQWeChatRalation
    public void savePhoenRelation(PhoneQQWeChatRalation phoneRelation) throws Exception;

    //条件查询是否已经录入库
    public List<String> queryIdRelationLocal(RelationLocalFilter filter) throws Exception;
    public List<String> queryPhoneRelationLocal(RelationLocalFilter filter) throws Exception;

}
