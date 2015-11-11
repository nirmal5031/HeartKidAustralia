package com.heartkid.controller;

import java.util.ArrayList;
import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.service.HeartkidExportService;
import com.heartkid.service.SearchService;
import com.heartkid.util.ExcelBuilder;

@RestController
public class AdminController {
	@Autowired
	private ExcelBuilder excelView;
	@Autowired
    SearchService searchservice = new SearchService();
	List<RegisterDtoEntity> searchdto =  new ArrayList<RegisterDtoEntity>();	
	@RequestMapping(value="heartkid/getrecord", method=RequestMethod.POST)
	public  List<RegisterDtoEntity> getrecordheartkid(@RequestBody RegisterDtoEntity searchentity){
		
		 String jsonInString = null;
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
		
		 String jsonInString = null;
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


}
