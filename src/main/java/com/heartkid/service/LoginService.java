package com.heartkid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heartkid.model.dto.*;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.repository.HeartkidLoginRepository;
import com.heartkid.util.EncrptDecryptPassword;


@Service
public class LoginService {
	
	@Autowired
	private HeartkidLoginRepository repository;

	 LoginEntity loginentity =  new LoginEntity();
	
	
	 public LoginEntity validateUser(String heartkidUsername, String password) {
		
		 String errorMessage;
		 String newpassword = null;
		 int useridexist = 0;
		 try
		 {
			 
			 if(heartkidUsername != null){
				 useridexist = repository.checkuserID(heartkidUsername);
			 }
			 if(useridexist==1)
			 {
		    loginentity = repository.findOne(heartkidUsername);
			newpassword = EncrptDecryptPassword.decrypt(loginentity.getPassword());
			System.out.println("New pass------->>>>"+newpassword);
		
		  if((loginentity.getUsername().equalsIgnoreCase(heartkidUsername)) & (newpassword.equals(password)))
				 {
			  
			  loginentity.setStatus("success");
			// loginentity.setErrorMessage(errorMessage);		
		 }
		  else if((loginentity.getUsername().equalsIgnoreCase(heartkidUsername)) & (newpassword != password))
				
			{
			 loginentity.setStatus("INVALIDCREDENTIALS");
			// errorMessage = "INVALID CREDENTIALS";
			// loginentity.setErrorMessage(errorMessage);
			
			 }
		 
		 }
			 else
		 {
			 loginentity.setStatus("NOUSER");
		 }
			 }
		 catch (NullPointerException nullPointer)
		 {
			 errorMessage = "USER NOT REGISTERED";
			 loginentity.setStatus("NOUSER");
			System.out.println("Empty value-- not found"+errorMessage);
			/*	loginentity.setHeartkidNumber(heartkidUsername);*/
			
			}
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
			
			 
		 }

		 return loginentity;
	 }
}
