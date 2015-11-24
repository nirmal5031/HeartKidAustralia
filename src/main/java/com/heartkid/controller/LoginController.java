package com.heartkid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heartkid.model.dto.LoginDetail;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.service.LoginService;
import com.heartkid.util.Constants;

@RestController
public class LoginController {
	
    @Autowired
    private LoginService loginService;
    LoginEntity loginEntity =new LoginEntity();

	 @RequestMapping(value = Constants.LOGIN_REST_API+"/{staffnumber}/{password}", method = RequestMethod.POST)
	    public LoginEntity validateUser(/*@RequestBody LoginDetail loginDetail)*/
	    		 @PathVariable("staffnumber") final String staffnumber,
	             @PathVariable("password") final String password){
	      
	       loginEntity = loginService.validateUser(staffnumber, password);
	       System.out.println("login status--->"+loginEntity.getStatus()+"-----ERROR MESSAGE-----"+loginEntity.getErrorMessage());
	       //  UserValidation.validateUser(loginDetail.getHeartkidNumber());
	        return loginEntity;
	    }
	 
}
