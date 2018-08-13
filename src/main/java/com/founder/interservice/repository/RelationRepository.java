package com.founder.interservice.repository;

import com.founder.interservice.model.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface RelationRepository extends JpaRepository<Relation,String>{



}
