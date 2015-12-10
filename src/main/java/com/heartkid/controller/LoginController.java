package com.heartkid.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heartkid.model.entity.LoginEntity;
import com.heartkid.service.LoginService;
import com.heartkid.util.Constants;

@RestController
public class LoginController {
	
    @Autowired
    private LoginService loginService;
    LoginEntity loginEntity =new LoginEntity();

	 @RequestMapping(value = Constants.LOGIN_REST_API+"/{heartkidnum}/{password}", method = RequestMethod.POST)
	    public LoginEntity validateUser(/*@RequestBody LoginDetail loginDetail)*/
	    		 @PathVariable("heartkidnum") final String heartkidnum,
	             @PathVariable("password") final String password){
	      
	       loginEntity = loginService.validateUser(heartkidnum, password);
	       System.out.println("login status--->"+loginEntity.getStatus()+"-----ERROR MESSAGE-----"+loginEntity.getStatus());
	       int loginflag  =  loginEntity.getLoginflag();
	       System.out.println("Login entity flag is "+loginflag);
	       //  UserValidation.validateUser(loginDetail.getHeartkidNumber());
	        return loginEntity;
	    }
	 
	 @RequestMapping(value="heartkid/resetnewpassword", method=RequestMethod.POST)
		public  LoginEntity Resetnewpassword(@RequestBody LoginEntity loginentity){
		System.out.println("loginentity.get"+loginentity.getPassword()+"---new password----"+loginentity.getNewpassword()+"username---"+loginentity.getUsername());
		LoginEntity response = null;
		try
		{
			String pass = loginentity.getPassword();
					String newpass= loginentity.getNewpassword();
					String username= loginentity.getUsername();
			 response = loginService.resetpassword(username,pass,newpass);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
return response;
	 }
	 	 
	 
}
