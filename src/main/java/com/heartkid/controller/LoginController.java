package com.heartkid.controller;

import com.heartkid.model.entity.LoginEntity;
import com.heartkid.service.LoginService;
import com.heartkid.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    LoginEntity loginEntity = new LoginEntity();
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = Constants.LOGIN_REST_API + "/{heartkidnum}/{password}", method = RequestMethod.POST)
    public LoginEntity validateUser(
            @PathVariable("heartkidnum") final String heartkidnum,
            @PathVariable("password") final String password) {

        loginEntity = loginService.validateUser(heartkidnum, password);
        LOGGER.info("login status--->" + loginEntity.getStatus() + "-----Login Status-----" + loginEntity.getStatus());
        return loginEntity;
    }

    @RequestMapping(value = "heartkid/resetnewpassword", method = RequestMethod.POST)
    public LoginEntity ResetNewPassword(@RequestBody LoginEntity loginentity) {
        LoginEntity response = null;
        try {
            LOGGER.info("Resetting password for User-" + loginentity.getUsername());
            String pass = loginentity.getPassword();
            String newpass = loginentity.getNewpassword();
            String username = loginentity.getUsername();
            response = loginService.resetpassword(username, pass, newpass);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
