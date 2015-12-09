package com.heartkid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heartkid.model.entity.CreateAdminUser;


@Transactional
@Repository
public interface CreateAdminRepository extends CrudRepository<CreateAdminUser, Long> {

	@Query("select count(u) from CreateAdminUser u where u.username=:username")
	int adminuserexist (@Param(value = "username") final String username);
	
	  @Query("delete from CreateAdminUser u where u.username=:deleteuseradmin")
		String deleteUsersadmin( @Param(value = "deleteuseradmin") final String deleteuseradmin);


}