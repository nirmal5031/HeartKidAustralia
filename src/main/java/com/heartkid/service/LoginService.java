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
	
	 
	 public LoginEntity validateUser(LoginDetail loginDetail) {
		 String  heartkidUsername = loginDetail.getHeartkidNumber();
		 String errorMessage;
		 try
		 {
		 loginentity = repository.findOne(heartkidUsername);
		 if(loginentity == null){
			 errorMessage = "Username is not found";
			 loginentity.setErrorMessage(errorMessage);
			
		 }
		 else if(loginentity.getPassword().equals(loginDetail.getPassword()))
				 {
			 errorMessage = "Success";
			 loginentity.setErrorMessage(errorMessage);		
		 }else
				 {
			 errorMessage = "Username or Password is wrong ! Please try again";
			 loginentity.setErrorMessage(errorMessage);
			
			 }
		 
		 }
		 catch (NullPointerException nullPointer)
		 {
			 errorMessage = "Username is not registered ! Please try with valid credentials ! ";
				
			System.out.println("Empty value-- not found"+errorMessage);
			/*	loginentity.setHeartkidNumber(heartkidUsername);
			loginentity.setErrorMessage(errorMessage);*/
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }

		 return loginentity;
	 }
}
