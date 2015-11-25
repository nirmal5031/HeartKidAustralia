package com.heartkid.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.heartkid.model.entity.CreateAdminUser;


@Transactional
@Repository
public interface CreateAdminRepository extends CrudRepository<CreateAdminUser, Long> {

	

}