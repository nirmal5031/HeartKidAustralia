package com.heartkid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heartkid.model.dto.*;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.repository.HeartkidLoginRepository;


@Service
public class LoginService {
	
	@Autowired
	private HeartkidLoginRepository repository;

	 LoginEntity loginentity =  new LoginEntity();
	
	
	 public LoginEntity validateUser(String heartkidUsername, String password) {
		
		 String errorMessage;
		 try
		 {
		 loginentity = repository.findOne(heartkidUsername);
		 
		  if((loginentity.getUsername().equalsIgnoreCase(heartkidUsername)) & (loginentity.getPassword().equals(password)))
				 {
			  loginentity.setStatus("success");
			// loginentity.setErrorMessage(errorMessage);		
		 }
		  else if((loginentity.getUsername().equalsIgnoreCase(heartkidUsername)) & (loginentity.getPassword() != password))
				
			{
			 loginentity.setStatus("INVALIDCREDENTIALS");
			// errorMessage = "INVALID CREDENTIALS";
			// loginentity.setErrorMessage(errorMessage);
			
			 }
		 
		 }
		 catch (NullPointerException nullPointer)
		 {
			 errorMessage = "USER NOT REGISTERED";
			 loginentity.setStatus("NOUSER");
			System.out.println("Empty value-- not found"+errorMessage);
			/*	loginentity.setHeartkidNumber(heartkidUsername);*/
			
			}
		 
		/* catch(Exception e)
		 {
			 e.printStackTrace();
			 errorMessage = "OtherError";
				
			 loginentity.setErrorMessage(errorMessage);
			 
		 }*/

		 return loginentity;
	 }
}
