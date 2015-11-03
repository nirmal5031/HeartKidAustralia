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
	
	 public String validateUser(LoginDetail loginDetail) {
		 try
		 {
		 String  heartkidUsername = loginDetail.getHeartkidNumber();
		 String errorMessage;
		 loginentity = repository.findOne(heartkidUsername);
		 if(loginentity.getHeartkidNumber() == null){
			 System.out.println("Username is not found");
			 errorMessage = "Username is not found";
		 }
		 if(loginentity.getPassword().equals(loginDetail.getPassword()))
				 {
			 System.out.println("Sucess"+loginentity.getPassword());
			 errorMessage = "Success";
			
		 }else
				 {
			 System.out.println("failure credentials");
			 errorMessage = "Username or Password is wrong ! Please try again";
			 }
		 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }

		 return errorMessage;
	 }
}
