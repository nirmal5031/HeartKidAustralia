package com.heartkid.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartkid.model.dto.RegisterDto;
import com.heartkid.model.entity.BurdenDiseaseEntity;
import com.heartkid.model.entity.DiseaseQuantificationEntity;
import com.heartkid.model.entity.OutHospitalEntity;
import com.heartkid.model.entity.PersonalInfoEntity;
import com.heartkid.model.entity.ProductivityEducationEntity;
import com.heartkid.model.entity.QualityCareEntity;
import com.heartkid.repository.BurdenDiseaseRepository;
import com.heartkid.repository.DiseaseQuantRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.repository.OutHospitalRepository;
import com.heartkid.repository.PersonalInfoRepository;
import com.heartkid.repository.ProductivityEduRepository;
import com.heartkid.repository.QualityCareRepository;
import com.heartkid.service.EditHearkidUserService;

@RestController

public class HeartkidController {

@Autowired
private  HeartkidRepository repository;

@Autowired
private  PersonalInfoRepository personalrepository;


@Autowired
private  DiseaseQuantRepository diseaserepository;

@Autowired
private  BurdenDiseaseRepository burdenrepository;


@Autowired
private EditHearkidUserService edituserrecord;


@Autowired
private ProductivityEduRepository prodedurepository;

@Autowired
QualityCareRepository qualitycarerepository;


@Autowired
OutHospitalRepository outhospitalrepository;

RegisterDto registrdto = new RegisterDto ();
PersonalInfoEntity personalinfo = new PersonalInfoEntity();
ObjectMapper mapper = new ObjectMapper();
@RequestMapping(value="heartkid/personalinfo", method=RequestMethod.POST)
public  String savepersonalInfo(@RequestBody PersonalInfoEntity personalinfo){
	 try{
	        if (personalinfo != null){
	        	personalrepository.save(personalinfo);
                System.out.println("SUCCESS"+ personalinfo.getId());
	        }
	        else
	        	System.out.println("personalinfo is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id = " + personalinfo.getId() + ")";
	  }

@RequestMapping(value="heartkid/diseasequant", method=RequestMethod.POST)
public  String savediseasequant(@RequestBody DiseaseQuantificationEntity diseasequant){
	 try{
	        if (diseasequant != null){
	        	System.out.println("Suregery details ---->"+diseasequant.getSurgeryHeld()+diseasequant.getTrvlsurg());
	        	diseaserepository.save(diseasequant);
                System.out.println("SUCCESS");
	        }
	        else
	        	System.out.println("personalinfo is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+diseasequant.getId()+")";
	  }

@RequestMapping(value="heartkid/burdendisease", method=RequestMethod.POST)
public  String saveburdendisease(@RequestBody BurdenDiseaseEntity burdendisease){
	 try{
	        if (burdendisease != null){
	        	burdenrepository.save(burdendisease);
                System.out.println("SUCCESS");
	        }
	        else
	        	System.out.println("burden disease is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+burdendisease.getId()+")";
	  }

@RequestMapping(value="heartkid/producteducation", method=RequestMethod.POST)
public  String saveburdendisease(@RequestBody ProductivityEducationEntity producteduentity){
	 try{
	        if (producteduentity != null){
	        	prodedurepository.save(producteduentity);
                System.out.println("SUCCESS");
	        }
	        else
	        	System.out.println("burden disease is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+producteduentity.getId()+")";
	  }


@RequestMapping(value="heartkid/qualitycare", method=RequestMethod.POST)
public  String saveburdendisease(@RequestBody QualityCareEntity qualitycareentity){
	 try{
	        if (qualitycareentity != null){
	        	qualitycarerepository.save(qualitycareentity);
                System.out.println("SUCCESS");
	        }
	        else
	        	System.out.println("burden disease is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+qualitycareentity.getId()+")";
	  }

@RequestMapping(value="heartkid/outhospital", method=RequestMethod.POST)
public  String saveburdendisease(@RequestBody OutHospitalEntity outhospitalentity){
	 try{
	        if (outhospitalentity != null){
	        	outhospitalrepository.save(outhospitalentity);
                System.out.println("SUCCESS");
	        }
	        else
	        	System.out.println("burden disease is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+outhospitalentity.getId()+")";
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
	 


