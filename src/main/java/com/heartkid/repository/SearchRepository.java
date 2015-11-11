package com.heartkid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heartkid.model.entity.*;


@Transactional
@Repository
public interface SearchRepository extends CrudRepository<RegisterDtoEntity, String> {
	
	 @Query("SELECT t FROM RegisterDtoEntity t WHERE t.referencenumber= IFNULL(:referencenumber,referencenumber) AND t.usertype= IFNULL(:usertype,usertype) AND t.firstname= IFNULL(:firstname,firstname) AND t.lastname = IFNULL(:lastname,lastname) AND t.countrybirth = IFNULL(:countrybirth,countrybirth) AND t.surveystatus = IFNULL(:status,surveystatus)")
			    public List<RegisterDtoEntity> findbysearchheartkid(@Param("referencenumber") String referencenumber,@Param("usertype") String usertype,@Param("firstname") String firstname,@Param("lastname") String lastname,@Param("countrybirth") String countrybirth,@Param("status") String status);
	 //@Param("firstname") String firstname,@Param("lastname") String lastname,@Param("countrybirth") String countrybirth
}