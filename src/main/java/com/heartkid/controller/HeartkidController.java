package com.heartkid.controller;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.BurdenDiseaseRepository;
import com.heartkid.repository.DiseaseQuantRepository;
import com.heartkid.repository.HeartKidReportRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.repository.OutHospitalRepository;
import com.heartkid.repository.PersonalInfoRepository;
import com.heartkid.repository.ProductivityEduRepository;
import com.heartkid.repository.QualityCareRepository;
import com.heartkid.service.EditHearkidUserService;
import com.heartkid.service.HeartkidMailingService;
import com.heartkid.util.Dategenerator;
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
private Dategenerator dategenerator;

@Autowired
private  PersonalInfoRepository personalrepository;

@Autowired
private EditHearkidUserService edituserrecord;
@Autowired
private ReferenceNumGenerator refnumber;

@Autowired
private RandomNumGenerator randomnumber;

@Autowired
private HeartKidReportRepository heartkidrepository;

@Autowired
private ObjectMapper mapper;


@Autowired
private HeartkidMailingService mailingclass;



@RequestMapping(value="heartkid/regcount", method=RequestMethod.GET)
public  String registrationcount(){
	String regcount;
	try
	{
	 int regcountnumber = personalrepository.registationcount();
	 regcount = Integer.toString(regcountnumber);
	}
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	return regcount;	
}

@RequestMapping(value="heartkid/regcount1", method=RequestMethod.GET)
public  String registrationcount1(){
	LOGGER.info("Report Screen for the request-----> ::" );
	String regcount1;
	String carerCountNo;
	String LovedoneNo;
	try
	{
	 String regcountnumber = heartkidrepository.patienttioncount();
	 String carercount = heartkidrepository.carercount();
	 String Lovedone = heartkidrepository.lovedcount();
	 regcount1=regcountnumber;
	 carerCountNo= carercount;
	 LovedoneNo = Lovedone;
	}
	 catch (Exception ex) {
	      return "Error creating the entry: " + ex.toString();
	    }
	regcount1 = regcount1.concat(",");
	regcount1 = regcount1.concat(carerCountNo.concat(","));
	regcount1 = regcount1.concat(LovedoneNo);
	return regcount1;	
}


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
		 personalinfo.setRegistrationdate(dategenerator.dategenerator());
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
		 diseasequant.setRegistrationdate(dategenerator.dategenerator());
	        if (diseasequant != null){
	        	personalrepository.save(diseasequant);
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
	
	LOGGER.info("Save burden Disease of the request :::::::::::" +burdendisease.getId());
	 try{
		 burdendisease.setRegistrationdate(dategenerator.dategenerator());
	     
	        if (burdendisease != null){
	        	personalrepository.save(burdendisease);
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
	LOGGER.info("Save for product education :::::::::::" +producteduentity.getId());
	 try{
		 producteduentity.setRegistrationdate(dategenerator.dategenerator());
	        if (producteduentity != null){
	        	personalrepository.save(producteduentity);
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
	LOGGER.info("Quality care for product education :::::::::::" +qualitycareentity.getId());
	 try{
		 
	        if (qualitycareentity != null){
	        	qualitycareentity.setRegistrationdate(dategenerator.dategenerator());
	   	     
	        	personalrepository.save(qualitycareentity);
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
	public  String outhospital(@RequestBody RegisterDtoEntity outhospitalentity) throws MessagingException{
	
	LOGGER.info("Quality care for product education :::::::::::" +outhospitalentity.getId());
	
	String response = null;
	 try{
	        if (outhospitalentity != null){
	        	outhospitalentity.setRegistrationdate(dategenerator.dategenerator());
	        	outhospitalentity.setSurveystatus("success");
	        	RegisterDtoEntity resp = personalrepository.save(outhospitalentity);
	        	System.out.println("MAIL TO USER "+resp.getEmail());
	        	if(resp.getSurveystatus() == "success")
	        	{
	        		response = "success";
	        		try
	        		{
	        			//mailingclass.mailingservice(resp.getEmail());
	        		}catch (Exception mex) {
	        	         mex.printStackTrace();
	        	      }
	        	}
	        	else
	        	{
	        		 response = "failure";
	        		   
	        	}
	        	
	        }
	        else
	        	 LOGGER.info("burden disease is null");
	 }
	 catch (Exception ex) {
		 response = "failure";
	    LOGGER.info(ex.toString());
	    }
	 
	    return response;
	  }
		
@RequestMapping(value="heartkid/updaterecord", method=RequestMethod.POST)
	public  String updaterecordheartkid(@RequestParam(value="updaterecordref", defaultValue="") String updaterecordref,@RequestBody RegisterDtoEntity registration){
	LOGGER.info("Update the record :::::::::::" +updaterecordref);
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




@RequestMapping(value="heartkid/sendmail", method=RequestMethod.GET)
public  String sendmail(){
	
	
	String response1 = mailingclass.mailingservice("nirmakumar@yahoo.com");
	return response1;
}
	}