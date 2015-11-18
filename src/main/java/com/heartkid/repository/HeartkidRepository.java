package com.heartkid.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.heartkid.model.entity.RegisterDtoEntity;


@Transactional
@Repository
public interface HeartkidRepository extends CrudRepository<RegisterDtoEntity, Long> {
	
	 @Query("select l from RegisterDtoEntity l where l.referencenumber = :referencenumber")
	 RegisterDtoEntity findByreferenceNum(
			 @Param(value = "referencenumber") final String referencenumber);
	/* @Modifying
	 @Query("update RegisterDto u set u.firstname = ?1, u.lastname = ?2 where u.referencenumber = ?3")
	 int updateByreferenceNum(String firstname, String lastname, String updaterecordref);
	*/ @Modifying
	    @Query("delete from RegisterDtoEntity u where  u.referencenumber = :deleterecordref")
	void deleteUsersByRefNumber( @Param(value = "deleterecordref") final String deleterecordref);

	

}