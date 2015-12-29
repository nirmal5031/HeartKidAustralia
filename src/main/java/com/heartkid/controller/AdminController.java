package com.heartkid.controller;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heartkid.model.entity.CreateAdminUser;
import com.heartkid.model.entity.LoginEntity;
import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.CreateAdminRepository;
import com.heartkid.repository.HeartKidReportRepository;
import com.heartkid.repository.HeartkidRepository;
import com.heartkid.service.LoginService;
import com.heartkid.service.SearchService;
import com.heartkid.util.EncrptDecryptPassword;
import com.heartkid.util.ExcelBuilder;

@RestController
public class AdminController {
	@Autowired
	private
	CreateAdminRepository createadminrepository;
	@Autowired
	private ExcelBuilder excelView;
	@Autowired
	private SearchService searchservice;
	@Autowired
	private HeartkidRepository repository;
	@Autowired
	private EncrptDecryptPassword encrpytdecrypt;
	@Autowired
	private HeartKidReportRepository heartkidrepository; 
	
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(HeartkidController.class);
	
	List<RegisterDtoEntity> searchdto =  new ArrayList<RegisterDtoEntity>();	
	@RequestMapping(value="heartkid/getrecord", method=RequestMethod.POST)
	public  List<RegisterDtoEntity> getrecordheartkid(@RequestBody RegisterDtoEntity searchentity){
		
		 //String jsonInString = null;
		 try{
			 System.out.println("Search referen"+searchentity.getReferencenumber());
			 System.out.println("Search userti"+searchentity.getUsertype());
				
			 searchdto = searchservice.searchheartkid(searchentity);
			 
		/*	 registrdto = edituserrecord.editheartkiduser(getrecordref);	
			 
			 if(registrdto!= null)
			 jsonInString = mapper.writeValueAsString(registrdto);*/
		 }
		 catch (Exception ex) {
		     System.out.println("ERROR in SEARCH"+ex.toString());
		    }
		 
		    return searchdto;
		  }
	
	@RequestMapping(value="heartkid/getrecordinexcel", method=RequestMethod.POST)
	public  List<RegisterDtoEntity> getrecordheartkidexcel(@RequestBody RegisterDtoEntity searchentity){
		
		
		 try{
			 System.out.println("Search referen"+searchentity.getReferencenumber());
			 System.out.println("Search userti"+searchentity.getUsertype());
				
			 searchdto = searchservice.searchheartkid(searchentity);
			 
		/*	 registrdto = edituserrecord.editheartkiduser(getrecordref);	
			 
			 if(registrdto!= null)
			 jsonInString = mapper.writeValueAsString(registrdto);*/
		 }
		 catch (Exception ex) {
		     System.out.println("ERROR in SEARCH"+ex.toString());
		    }
		 
		    return searchdto;
		  }
	
	
	 @RequestMapping(value = "heartkid/downloadExcel", method = RequestMethod.POST)
	    public ModelAndView downloadExcel(@RequestBody RegisterDtoEntity registerdto) {
		 System.out.println("Alert--model");
	        // create some sample data
		try{
		 searchdto = searchservice.searchheartkid(registerdto);
	 System.out.println("searchdto"+searchdto.get(0).getReferencenumber());
	        // return a view which will be resolved by an excel view resolver
	 if(searchdto.isEmpty())
	 {
		System.out.println("SEARCH DTO is EMPTY"+searchdto.isEmpty()); 
	 }
	 else
	 {
		 System.out.println("IS not empty");
	 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	        return new ModelAndView(excelView, "listheartkidusers", searchdto);
	    }
	 
	 @RequestMapping(value="heartkid/deleterecord/{deleterecordref}", method=RequestMethod.GET)
		public  String deleteUsersByRefNumber(@PathVariable(value="deleterecordref") String deleterecordref){
				 try{
					 LOGGER.info("delete record---"+deleterecordref);
					 repository.deleteUsersByRefNumber(deleterecordref);   
				 }
				 catch (Exception ex) {
				      return "Error creating the entry: " + ex.toString();
				    }
				 
				 	    return "User record Deleted successfully ! (Reference Id is = " +   deleterecordref+ ")";
			  }
	 
	 @RequestMapping(value="heartkid/createadminuser", method=RequestMethod.POST)
		public  String createadminuser(@RequestBody CreateAdminUser createuser){
			
			 String status = null;
			 int userexist = 0 ;
			 try{
				if(createuser != null)
				{
					 userexist = createadminrepository.adminuserexist(createuser.getUsername());
				System.out.println("=====userexist===="+userexist);
				}
				if(userexist == 0)
				{
					String encpass = EncrptDecryptPassword.encrypt(createuser.getPassword());
					if(encpass!=null)
					createuser.setPassword(encpass);
				 createadminrepository.save(createuser);
				 status = "success";
				}
				else
				{
					 status = "useridexist";
				}
						 }
			 catch (Exception ex) {
				 status = "fail";
			     System.out.println("ERROR in Creating user"+ex.toString());
			    }
			 
			    return status;
			  }

	 @RequestMapping(value="heartkid/tokenvalidate", method=RequestMethod.GET)
		public String Validateaccesstoken(){
		 String validatemessage = "tokenvalid";
		 return validatemessage;
		 
	 }
	 List<CreateAdminUser> listadminuser =  new ArrayList<CreateAdminUser>();	
	 @RequestMapping(value="heartkid/listadminuser", method=RequestMethod.GET)
	 public List<CreateAdminUser> Listadminuser(){
		 
		 listadminuser = (List<CreateAdminUser>) createadminrepository.findAll();
		 
		return listadminuser;
		 
		 
	 }
	 
	 @RequestMapping(value="heartkid/deleteadminuser/{delusername}", method=RequestMethod.GET)
		public  int Deleteadminuser(@PathVariable(value="delusername") String delusername){
		 int response = 0;
				 try{
		
					 response = createadminrepository.deleteUsersadmin(delusername);
					 System.out.println("Delete response ----"+response);
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				return response;
	 }
	 
	 List<CreateAdminUser> fetchadminuser  =  new ArrayList<CreateAdminUser>();	
	 @RequestMapping(value="heartkid/fetchadminuser/{username}", method=RequestMethod.GET)
		public  List<CreateAdminUser> fetchadminuser(@PathVariable(value="username") String username){
				 try{
		System.out.println("FETCH ADMIN USER"+username);
	
					 fetchadminuser = (List<CreateAdminUser>) createadminrepository.findOne(username);
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				return fetchadminuser;
	 }
	 @RequestMapping(value="heartkid/reportcount", method=RequestMethod.GET)
		public  String registrationcount1(){
			LOGGER.info("Report Screen for the request-----> ::" );
			String reportcount;
			String carerCountNo;
			String LovedoneNo;
			try
			{
			 String regcountnumber = heartkidrepository.patientcount();
			 String carercount = heartkidrepository.carercount();
			 String Lovedone = heartkidrepository.lovedcount();
			 reportcount=regcountnumber;
			 carerCountNo= carercount;
			 LovedoneNo = Lovedone;
			}
			 catch (Exception ex) {
			      return "Error creating the entry: " + ex.toString();
			    }
			reportcount = reportcount.concat(",");
			reportcount = reportcount.concat(carerCountNo.concat(","));
			reportcount = reportcount.concat(LovedoneNo);
			return reportcount;	
		}
	RegisterDtoEntity getuserdetails  =  new RegisterDtoEntity();	
	@RequestMapping(value="heartkid/getuserdetails/{id}", method=RequestMethod.GET)
	 public  RegisterDtoEntity getuserdetails(@PathVariable(value="id") String id){
		 try{
			 long d = Long.parseLong(id);
			 getuserdetails = repository.findOne(d);
			 
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
	 return getuserdetails;
}
}
