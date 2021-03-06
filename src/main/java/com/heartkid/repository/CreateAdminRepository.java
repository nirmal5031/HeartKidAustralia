package com.heartkid.repository;

import com.heartkid.model.entity.CreateAdminUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface CreateAdminRepository extends CrudRepository<CreateAdminUser, Long> {

    @Query("select count(u) from CreateAdminUser u where u.username=:username")
    int adminUserExist(@Param(value = "username") final String username);

    @Modifying
    @Query("delete from CreateAdminUser u where u.username=:deleteuseradmin")
    int deleteUsersadmin(@Param(value = "deleteuseradmin") final String deleteuseradmin);

    @Query("select u from CreateAdminUser u where u.username=:deleteuseradmin")
    List<CreateAdminUser> findOne(@Param(value = "deleteuseradmin") final String deleteuseradmin);


}