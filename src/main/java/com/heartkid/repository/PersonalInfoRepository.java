package com.heartkid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.heartkid.model.entity.*;


@Transactional
@Repository
public interface PersonalInfoRepository extends CrudRepository<RegisterDtoEntity, String> {
	
	@Query("select count(u) from RegisterDtoEntity u")
	int registationcount ();
}