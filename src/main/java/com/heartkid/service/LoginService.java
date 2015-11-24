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
	
	
	 public LoginEntity validateUser(String staffnumber, String password) {
		 String  heartkidUsername = staffnumber;
		 String errorMessage;
		 try
		 {
		 loginentity = repository.findOne(heartkidUsername);
		 
		  if((loginentity.getHeartkidNumber().equalsIgnoreCase(heartkidUsername)) & (loginentity.getPassword().equals(password)))
				 {
			  loginentity.setStatus(true);
			 errorMessage = "SUCCESS";
			 loginentity.setErrorMessage(errorMessage);		
		 }
		  else if((loginentity.getHeartkidNumber().equalsIgnoreCase(heartkidUsername)) & (loginentity.getPassword() != password))
				
			{
			 loginentity.setStatus(false);
			 errorMessage = "INVALID CREDENTIALS";
			 loginentity.setErrorMessage(errorMessage);
			
			 }
		 
		 }
		 catch (NullPointerException nullPointer)
		 {
			 errorMessage = "USER NOT REGISTERED";
				
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
