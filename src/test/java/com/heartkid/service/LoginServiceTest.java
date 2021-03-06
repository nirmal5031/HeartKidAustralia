package com.heartkid.service;

import com.heartkid.ServiceTest;
import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.repository.HeartkidLoginRepository;
import com.heartkid.util.AdminUserBuilder;
import com.heartkid.util.EncrptDecryptPassword;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class LoginServiceTest extends ServiceTest {

    LoginEntity loginentity = new LoginEntity();

    @Autowired
    private HeartkidLoginRepository heartkidLoginRepository;

    @Autowired
    private CreateAdminRepository createAdminRepository;

    @Autowired
    private LoginService loginService;


    @Before
    public void setUp() throws Exception {
        createAdminRepository.deleteAll();

        final CreateAdminUser createAdminUser = new AdminUserBuilder().defaultValues();
        createAdminUser.setPassword(EncrptDecryptPassword.encrypt("Test"));
        createAdminRepository.save(createAdminUser);
    }

    @Test
    public void validateUser() throws Exception {

        final LoginEntity loginEntity = loginService.validateUser("Test", "Test");

        assertEquals(loginEntity.getStatus(), "success");
    }

    @Test
    public void returnErrorInvalidUserName() throws Exception {

        final LoginEntity loginEntity = loginService.validateUser("Testing", "Test");

        assertEquals(loginEntity.getStatus(), "NOUSER");
    }


    @Test
    public void returnErrorInvalidPassword() throws Exception {

        final LoginEntity loginEntity = loginService.validateUser("Test", "Testing");

        assertEquals(loginEntity.getStatus(), "INVALIDCREDENTIALS");
    }

    @Test
    public void resetPassword() throws Exception {

        final LoginEntity loginEntity = loginService.resetpassword("Test", "Test", "New");

        assertEquals(loginEntity.getStatus(), "RESETSUCCESS");
    }

    @Test
    public void returnErrorForEmptyPassword() throws Exception {

        final LoginEntity loginEntity = loginService.resetpassword("Test", "Test", "");

        assertEquals(loginEntity.getStatus(), "RESETSUCCESS");
    }


}
