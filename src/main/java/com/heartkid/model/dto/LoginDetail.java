package com.heartkid.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * Domain class for LoginDetail
 * 
 * @author TCS
 * @version 1.0
 */

@AutoProperty
public class LoginDetail {

	
	    private String heartkidNumber;
	    private String password;
	 /*   private String userrole;
	    private String name;
	    private String address;
	    private String emailid;
	    private String phone;*/
	    
	    

	    /**
	     * Default constructor for domain class LoginDetail
	     */
	    public LoginDetail() {
	    }

	    /**
	     * Constructor for domain class LoginDetail
	     * 
	    
	     */
	    public LoginDetail(final String heartkidNumber,
	            final String password) {
	        this.heartkidNumber = heartkidNumber;
	        this.password = password;
	       /* this.name = name;
	        this.address=address;
	        this.userrole=userrole;
	        this.emailid=emailid;
	        this.phone=phone;*/
	        
	    }

	   
	    public void setHeartkidNumber(String heartkidNumber) {
			this.heartkidNumber = heartkidNumber;
		}
	  
	    public void setPassword(String password) {
	        this.password = password;
	    }

	 

	    public String getPassword() {
	        return password;
	    }

	  
		public String getHeartkidNumber() {
			return heartkidNumber;
		}

		
		
  
	}