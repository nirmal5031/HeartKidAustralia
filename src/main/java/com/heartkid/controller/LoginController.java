package com.heartkid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.heartkid.model.dto.LoginDetail;
import com.heartkid.util.Constants;
import com.heartkid.util.UserValidation;
import com.heartkid.service.LoginService;


public class LoginController {
	
    @Autowired
    private LoginService loginService;
    
	 @RequestMapping(value = Constants.LOGIN_REST_API, method = RequestMethod.POST)
	    public LoginDetail validateUser(@RequestBody LoginDetail loginDetail) {
	       
	        UserValidation.validateUser(loginDetail.getHeartkidNumber());
	        return loginService.validateUser(loginDetail).get();
	    }
	 
}
