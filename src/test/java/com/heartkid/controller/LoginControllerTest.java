package com.heartkid.controller;

import com.heartkid.RestControllerTest;
import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.util.AdminUserBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest extends RestControllerTest {

    public static final String LOGIN_REST_API = "/heartkid/login/{heartkidnum}/{password}";
    public static final String HEARTKID_CREATEADMINUSER = "/heartkid/createadminuser";
    public static final String RESET_PASSWORD = "/heartkid/resetnewpassword";

    @Autowired
    CreateAdminRepository createAdminRepository;

    CreateAdminUser createAdminUser = null;

    @Before
    public void setUp() throws Exception {
        initializeMockMvc();
        createAdminRepository.deleteAll();
        createAdminUser = new AdminUserBuilder().defaultValues();
        mockMvc.perform(post(HEARTKID_CREATEADMINUSER)
                .content(json(createAdminUser))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")))
                .andReturn();
    }

    @Test
    public void loginAsAdminUser() throws Exception {

        mockMvc.perform(post(LOGIN_REST_API, "Test", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Test")));
    }

    @Test
    public void loginAsInvalidAdminUser() throws Exception {

        mockMvc.perform(post(LOGIN_REST_API, "Testing", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("NOUSER")));
    }

    @Test
    public void loginAsInvalidPassword() throws Exception {

        mockMvc.perform(post(LOGIN_REST_API, "Test", "invalid"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("INVALIDCREDENTIALS")));
    }

    @Test
    public void resetNewPassword() throws Exception {

        mockMvc.perform(post(RESET_PASSWORD)
                .content(json(getLoginEntity()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RESETSUCCESS")))
                .andReturn();
    }

    @Test
    public void returnErrorForInvalidPassword() throws Exception {

        mockMvc.perform(post(RESET_PASSWORD)
                .content(json(null))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("")))
                .andReturn();
    }

    private LoginEntity getLoginEntity() {
        final LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername("Test");
        loginEntity.setPassword("Test");
        loginEntity.setNewpassword("New");
        return loginEntity;
    }
}
