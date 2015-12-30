package com.heartkid.repository;

import com.heartkid.model.entity.LoginEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface HeartkidLoginRepository extends CrudRepository<LoginEntity, String> {

    @Query("select count(l) from LoginEntity l where l.username = :username")
    int checkuserID(@Param(value = "username") final String username);

    @Modifying
    @Query("update LoginEntity u set u.password = :newpassword,u.loginflag = :loginflag where u.username = :username")
    int updateresetpassword(@Param(value = "username") final String username, @Param(value = "newpassword") final String newpassword, @Param(value = "loginflag") final int loginflag);

}



