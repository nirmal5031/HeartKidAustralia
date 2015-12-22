package com.heartkid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heartkid.model.entity.RegisterDtoEntity;

@Transactional
@Repository
public interface HeartKidReportRepository extends CrudRepository<RegisterDtoEntity, String> {

	@Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='patient'")
	String patientcount ();

	@Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='carer'")
	String carercount();

	@Query("select count(usertype) as pga from RegisterDtoEntity u where  usertype='Filling this form on behalf of your passed loved one'")
	String lovedcount();
}