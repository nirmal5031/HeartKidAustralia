package com.heartkid.service;

import com.heartkid.ServiceTest;
import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.util.AdminUserBuilder;
import com.heartkid.util.RequestBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;


public class AdminServiceTest extends ServiceTest {

    @Autowired
    private CreateAdminRepository createAdminRepository;

    @Autowired
    private HeartkidRepository heartkidRepository;

    @Autowired
    private AdminService adminService;

    @Test
    public void deleteUserByReferenceNumber() throws Exception {

        RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        assertEquals(adminService.deleteUserByReferenceNumber("ABCD1234"), 1);
    }

    @Test
    public void nodeleteUserForInvalidReferenceNumber() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        assertEquals(adminService.deleteUserByReferenceNumber("XXXXXXXXXX"), 0);
    }

    @Test
    public void doesUserExist() throws Exception {
        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();
        createAdminRepository.save(createAdminUser);

        assertEquals(adminService.doesUserExist(createAdminUser.getUsername()), 1);
    }

    @Test
    public void returnUserNotExist() throws Exception {

        assertEquals(adminService.doesUserExist("XXXXXXXXXX"), 0);
    }

    @Test
    public void saveAdminUser() throws Exception {
        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();

        final CreateAdminUser savedAdminUser = adminService.saveAdminUser(createAdminUser);

        assertEquals(createAdminUser.getFirstname(), savedAdminUser.getFirstname());
        assertEquals(createAdminUser.getUsername(), savedAdminUser.getUsername());
    }

    @Test
    public void findAllUser() throws Exception {

        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();
        createAdminRepository.save(createAdminUser);

        final List<CreateAdminUser> allUser = (List<CreateAdminUser>) adminService.findAllUser();
        assertThat(allUser, hasSize(1));
    }

    @Test
    public void deleteUseradmin() throws Exception {

        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();
        createAdminRepository.save(createAdminUser);

        assertEquals(adminService.deleteUseradmin(createAdminUser.getUsername()), 1);
    }

    @Test
    public void deleteInvalidUser() throws Exception {

        assertEquals(adminService.deleteUseradmin("XXXXXXXXXXXXXX"), 0);
    }

    @Test
    public void findAdminUser() throws Exception {

        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();
        createAdminRepository.save(createAdminUser);

        final List<CreateAdminUser> allUser = (List<CreateAdminUser>) adminService.findAdminUser(createAdminUser.getUsername());
        assertThat(allUser, hasSize(1));
    }

    @Test
    public void returnNoRecordsForInvalidUser() throws Exception {

        final List<CreateAdminUser> allUser = (List<CreateAdminUser>) adminService.findAdminUser("XXXXXXXXXXXXXXXXX");
        assertThat(allUser, hasSize(0));
    }

    @Test
    public void getLovedCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        assertEquals(adminService.getLovedcount(), "0");
    }

    @Test
    public void getCarerCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        assertEquals(adminService.getCarercount(), "0");
    }

    @Test
    public void getPatientCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        registerDtoEntity.setUsertype("patient");
        heartkidRepository.save(registerDtoEntity);

        assertEquals(adminService.getPatientcount(), "1");
    }

    @Test
    public void getLovedBarCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        final List<String> count = (List<String>) adminService.getLovedbarcount();
        assertThat(count, hasSize(0));
    }

    @Test
    public void getCarerBarCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        final List<String> count = (List<String>) adminService.getCarerbarcount();
        assertThat(count, hasSize(0));
    }

    @Test
    public void getPatientBarCount() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        final List<String> count = (List<String>) adminService.getPatientbarcount();
        assertThat(count, hasSize(1));
    }

    @Test
    public void findUserById() throws Exception {

        final RegisterDtoEntity registerDtoEntity = new RequestBuilder().defaultValues();
        heartkidRepository.save(registerDtoEntity);

        final RegisterDtoEntity newRegisterDtoEntity = adminService.findUser(1);
        assertEquals(newRegisterDtoEntity.getFirstname(), "Test");

    }

}
