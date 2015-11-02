package com.heartkid.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartkid.model.dto.RegisterDto;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.service.EditHearkidUserService;

@RestController

public class HeartkidController {

@Autowired
private  HeartkidRepository repository;
@Autowired
private EditHearkidUserService edituserrecord;

RegisterDto registrdto = new RegisterDto ();
ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value="heartkid/register", method=RequestMethod.POST)
	public  String registraUtente(@RequestBody RegisterDto registration){
		 System.out.println("creating!");
		 try{
		        if (registration != null){
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
	
	
	@RequestMapping(value="heartkid/getrecord", method=RequestMethod.GET)
	
	public  String getrecordheartkid(@RequestParam(value="getrecordref", defaultValue="") String getrecordref){
		
		 String jsonInString = null;
		 try{
			 registrdto = edituserrecord.editheartkiduser(getrecordref);	
			 
			 if(registrdto!= null)
			 jsonInString = mapper.writeValueAsString(registrdto);
		 }
		 catch (Exception ex) {
		      return "Error  the entry: " + ex.toString();
		    }
		 
		    return "User record edited successfully ! (reference Id is = " +jsonInString  +""+registrdto.getId()+ ")";
		  }
	
@RequestMapping(value="heartkid/updaterecord", method=RequestMethod.POST)
	
	public  String updaterecordheartkid(@RequestParam(value="updaterecordref", defaultValue="") String updaterecordref,@RequestBody RegisterDto registration){
			 try{
			        if (registration != null){
			        	repository.save(registration);
	                    System.out.println("SUCCESS");
			        }
			        else
			        	System.out.println("registration is null");
			 }
			 catch (Exception ex) {
			      return "Error creating the entry: " + ex.toString();
			    }
			 
			 	    return "User record edited successfully ! (reference Id is = " +   updaterecordref+ ")";
		  }




@RequestMapping(value="heartkid/deleterecord", method=RequestMethod.GET)
	
	public  String deleteUsersByRefNumber(@RequestParam(value="deleterecordref", defaultValue="") String deleterecordref){
			 try{
				 System.out.println("delete record---"+deleterecordref);
				 repository.deleteUsersByRefNumber(deleterecordref);   
			 }
			 catch (Exception ex) {
			      return "Error creating the entry: " + ex.toString();
			    }
			 
			 	    return "User record Deleted successfully ! (reference Id is = " +   deleterecordref+ ")";
		  }

	}
	 


