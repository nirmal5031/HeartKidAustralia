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
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.BurdenDiseaseRepository;
import com.heartkid.repository.DiseaseQuantRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.repository.OutHospitalRepository;
import com.heartkid.repository.PersonalInfoRepository;
import com.heartkid.repository.ProductivityEduRepository;
import com.heartkid.repository.QualityCareRepository;
import com.heartkid.service.EditHearkidUserService;
import com.heartkid.util.RandomNumGenerator;
import com.heartkid.util.ReferenceNumGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HeartkidController {

	private static final Logger LOGGER = LoggerFactory
            .getLogger(HeartkidController.class);
	
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
private QualityCareRepository qualitycarerepository;

@Autowired
private OutHospitalRepository outhospitalrepository;

@Autowired
private ReferenceNumGenerator refnumber;

@Autowired
private RandomNumGenerator randomnumber;

RegisterDto registrdto = new RegisterDto ();

@Autowired
ObjectMapper mapper;


@RequestMapping(value="heartkid/referencegen", method=RequestMethod.GET)
public  String generatereference(){
	 int randomCount = randomnumber.randomcountgenerator();
	 String referenceNumb = randomnumber.generateRandomString(randomCount).toUpperCase();
	 LOGGER.info("Reference Number for the request-----> ::"+referenceNumb);
	
	return referenceNumb;	
}


@RequestMapping(value="heartkid/personalinfo", method=RequestMethod.POST)
public  String savepersonalInfo(@RequestBody RegisterDtoEntity personalinfo){
	String personalinfoJSON = null;
	 try{
		 
	
		 LOGGER.info("Name of the request ::"+personalinfo.getFirstname()+" "+personalinfo.getLastname());
		 LOGGER.info("Referencenumber of the request :::::::::::"+personalinfo.getReferencenumber());
		
		if(personalinfo != null)
		    personalrepository.save(personalinfo);
		  
		    if(personalinfo!= null)
		    	personalinfoJSON = mapper.writeValueAsString(personalinfo);
				System.out.println(personalinfoJSON);
		   
	 }
	 catch (NullPointerException nullpointer)
	 {
		 return "{'value':'failure'}";
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return personalinfoJSON;
	  }


@RequestMapping(value="heartkid/diseasequant", method=RequestMethod.POST)
public  String savediseasequant(@RequestBody RegisterDtoEntity diseasequant){
	 try{
		 LOGGER.info("Referencenumber of the request :::::::::::"+diseasequant.getId());
		
	        if (diseasequant != null){
	        	diseaserepository.save(diseasequant);
              LOGGER.info("disease quantification added SUCCESS");
	        }
	        else
	        	System.out.println("Disease quantification is null");
	 }
	 catch (NullPointerException nullpointer)
	 {
		 return "No values present in the diserase quantification object: " + nullpointer.toString();
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+diseasequant.getId()+")";
	  }


@RequestMapping(value="heartkid/burdendisease", method=RequestMethod.POST)
public  String saveburdendisease(@RequestBody RegisterDtoEntity burdendisease){
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
public  String producteducation(@RequestBody RegisterDtoEntity producteduentity){
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
public  String qualitycare(@RequestBody RegisterDtoEntity qualitycareentity){
	 try{
	        if (qualitycareentity != null){
	        	qualitycarerepository.save(qualitycareentity);
                System.out.println("SUCCESS");
	        }
	        else
	        	LOGGER.info("quality care is null");
	 }
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	 
	    return "User succesfully created! (id ="+qualitycareentity.getId()+")";
	  }


@RequestMapping(value="heartkid/outhospital", method=RequestMethod.POST)
	public  String outhospital(@RequestBody RegisterDtoEntity outhospitalentity){
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



	
		
@RequestMapping(value="heartkid/updaterecord", method=RequestMethod.POST)
	public  String updaterecordheartkid(@RequestParam(value="updaterecordref", defaultValue="") String updaterecordref,@RequestBody RegisterDto registration){
			 try{
			        if (registration != null){
			        	repository.save(registration);
	                    System.out.println("SUCCESS");
			        }
			        else
			        	LOGGER.info("registration is null");
			 }
			 catch (Exception ex) {
			      return "Error creating the entry: " + ex.toString();
			    }
			 
			 	    return "User record edited successfully ! (reference Id is = " +updaterecordref+")";
		  }


@RequestMapping(value="heartkid/deleterecord", method=RequestMethod.GET)
	public  String deleteUsersByRefNumber(@RequestParam(value="deleterecordref", defaultValue="") String deleterecordref){
			 try{
				 LOGGER.info("delete record---"+deleterecordref);
				 repository.deleteUsersByRefNumber(deleterecordref);   
			 }
			 catch (Exception ex) {
			      return "Error creating the entry: " + ex.toString();
			    }
			 
			 	    return "User record Deleted successfully ! (reference Id is = " +   deleterecordref+ ")";
		  }
	}