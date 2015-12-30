package com.heartkid.service;

import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.repository.HeartkidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CreateAdminRepository createadminrepository;

    @Autowired
    private HeartkidRepository heartkidRepository;

    public void deleteUserByReferenceNumber(final String referenceNumber) {
        heartkidRepository.deleteUsersByRefNumber(referenceNumber);
    }

    public int doesUserExist(final String userName) {
        return createadminrepository.adminUserExist(userName);
    }

    public void saveAdminUser(final @RequestBody CreateAdminUser createuser) {
        createadminrepository.save(createuser);
    }

    public List<CreateAdminUser> findAllUser() {
        return (List<CreateAdminUser>) createadminrepository.findAll();
    }

    public int deleteUseradmin(final String userName) {
        return createadminrepository.deleteUsersadmin(userName);
    }

    public List<CreateAdminUser> findAdminUser(final String userName) {
        return (List<CreateAdminUser>) createadminrepository.findOne(userName);
    }

    public String getLovedcount() {
        return heartkidRepository.lovedcount();
    }

    public String getCarercount() {
        return heartkidRepository.carercount();
    }

    public String getPatientcount() {
        return heartkidRepository.patientcount();
    }

    public List getLovedbarcount() {
        return heartkidRepository.lovedbarcount();
    }

    public List getCarerbarcount() {
        return heartkidRepository.carerbarcount();
    }

    public List getPatientbarcount() {
        return heartkidRepository.patientbarcount();
    }

    public RegisterDtoEntity findUser(final long d) {
        return heartkidRepository.findOne(d);
    }

}
