package com.founder.interservice.regionalanalysis.repository;

import com.founder.interservice.regionalanalysis.model.Regional;
import com.founder.interservice.regionalanalysis.model.RegionalTaskResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName： RegionalRepository
 * @Auther： 曹鹏
 * @Description: java类作用描述
 * @CreateDate： 2018-08-22 14:32
 * @Version: 1.0
 */
public interface RegionalTaskResultRepository extends JpaRepository<RegionalTaskResult,String>,JpaSpecificationExecutor<RegionalTaskResult> {

}
