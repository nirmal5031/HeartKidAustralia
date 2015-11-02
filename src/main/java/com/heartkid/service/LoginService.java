package com.heartkid.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.heartkid.model.dto.*;
import com.heartkid.repository.HeartkidLoginRepository;
import com.heartkid.repository.HeartkidRepository;



public class LoginService {
	
	@Autowired
	private HeartkidLoginRepository repository;
	@Autowired
	private LoginDetail logindetail;
	
	 public LoginDetail validateUser(LoginDetail loginDetail) {
		 try
		 {
		 Long heartkidUsername = Long.parseLong(loginDetail.getHeartkidNumber());
		 
		 logindetail = repository.findOne(heartkidUsername);
		 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }
		 
		return loginDetail;
		 
		 
	 }
}
