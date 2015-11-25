package com.heartkid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heartkid.model.entity.*;


@Transactional
@Repository
public interface HeartkidLoginRepository extends CrudRepository<LoginEntity, String> {
	
@Query("select count(l) from LoginEntity l where l.username = :username")
int checkuserID (@Param(value = "username") final String username);
}