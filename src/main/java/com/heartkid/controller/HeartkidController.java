package com.heartkid.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heartkid.model.dto.RegisterDto;
import com.heartkid.model.entity.RegisterProfileEntity;
import com.heartkid.repository.RegistrationHeartkidDao;

@RestController

public class HeartkidController {

@Autowired
	  private  RegistrationHeartkidDao repository;

		
	@RequestMapping(value="heartkid/register", method=RequestMethod.POST)
	public  String registraUtente(@RequestBody RegisterDto registration){
		 System.out.println("creating!");
		 try{
		        if (registration != null){
		        	/*regprofile.setUsertype(registration.getUsertype());
		        	regprofile.setFirstname(registration.getFirstname());
		        	regprofile.setLastname(registration.getLastname());*/
		        	repository.save(registration);
                    System.out.println("SUCCESS");
		        }
		        else
		        	System.out.println("registration is null");
		 }
		 catch (Exception ex) {
		      return "Error creating the entry: " + ex.toString();
		    }
		 
		    return "User succesfully created! (id = " + registration.getId() + ")";
		  }
	}
	 


