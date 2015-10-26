package com.heartkid.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.heartkid.model.dto.RegisterDto;

@Transactional
@Repository
public interface RegistrationHeartkidDao extends CrudRepository<RegisterDto, Long> {
	
}
