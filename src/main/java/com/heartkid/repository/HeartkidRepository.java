package com.heartkid.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heartkid.model.dto.RegisterDto;


@Transactional
@Repository
public interface HeartkidRepository extends CrudRepository<RegisterDto, Long> {
	
	 @Query("select l from RegisterDto l where l.referencenumber = :referencenumber")
	 RegisterDto findByreferenceNum(
			 @Param(value = "referencenumber") final String referencenumber);
	 
	/* @Modifying
	 @Query("UPDATE  RegisterDto l set l.firstname=:firstname where l.referencenumber = :referencenumber")
	 Integer updateByreferenceNum(
			 @Param(value = "referencenumber") final String referencenumber,   @Param("firstname")final String firstname);
	*/
	 @Modifying
	 @Query("update RegisterDto u set u.firstname = ?1, u.lastname = ?2 where u.referencenumber = ?3")
	 int updateByreferenceNum(String firstname, String lastname, String updaterecordref);
}