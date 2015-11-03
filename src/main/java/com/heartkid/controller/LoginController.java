package com.heartkid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heartkid.model.dto.LoginDetail;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.service.LoginService;
import com.heartkid.util.Constants;
import com.heartkid.util.UserValidation;

@RestController
public class LoginController {
	
    @Autowired
    private LoginService loginService;
    LoginEntity loginEntity =new LoginEntity();

	 @RequestMapping(value = Constants.LOGIN_REST_API, method = RequestMethod.POST)
	    public String validateUser(@RequestBody LoginDetail loginDetail) {
	       System.out.println("loginDetail.getHeartkidNumber()------->"+loginDetail.getHeartkidNumber());
	      //  UserValidation.validateUser(loginDetail.getHeartkidNumber());
	        return loginService.validateUser(loginDetail);
	    }
	 
}
