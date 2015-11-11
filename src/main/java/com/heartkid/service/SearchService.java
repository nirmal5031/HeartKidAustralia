package com.heartkid.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.heartkid.model.entity.RegisterDtoEntity;
import com.heartkid.repository.SearchRepository;

@Service
public class SearchService {
	
	@Autowired
	SearchRepository searchrepository;
	List<RegisterDtoEntity> searchvalue = new ArrayList<RegisterDtoEntity>();
	String referencenumber;
	String usertype;
	String firstname;
	String lastname;
	String countrybirth;
	String status;
	public List<RegisterDtoEntity> searchheartkid(RegisterDtoEntity searchentity) {
		try
		{
		if(searchentity != null)
		{
		 referencenumber = searchentity.getReferencenumber();
		 usertype = searchentity.getUsertype();
		 firstname = searchentity.getFirstname();
		 lastname = searchentity.getLastname();
		 countrybirth = searchentity.getCountrybirth();
		 status = searchentity.getSurveystatus();
		}
		if(referencenumber == "")
			referencenumber=null;
		if(status == "")
			status=null;
		if(usertype == "")
			usertype=null;
		if(firstname == "")
			firstname=null;
		if(lastname == "")
			lastname=null;
		if(countrybirth == "")
			countrybirth=null;
		
		searchvalue = searchrepository.findbysearchheartkid(referencenumber, usertype,firstname,lastname,countrybirth,status);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return searchvalue;
	}

}
