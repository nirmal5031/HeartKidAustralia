package com.heartkid.model.dto;

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
@JsonInclude(Include.NON_NULL)
public class LoginDetail {

	
	    private String heartkidNumber;
	    private int loginAttempts;
	    private String password;
	    private String status;
	    

	    /**
	     * Default constructor for domain class LoginDetail
	     */
	    public LoginDetail() {
	    }

	    /**
	     * Constructor for domain class LoginDetail
	     * 
	     * @param staffNumber
	     * @param adminNumber
	     * @param loginAttempts
	     * @param password
	     * @param status
	     * @param operationDetail
	     */
	    public LoginDetail(final String heartkidNumber,
	            final int loginAttempts, final String password,
	            final String status) {
	        this.heartkidNumber = heartkidNumber;
	        this.loginAttempts = loginAttempts;
	        this.password = password;
	        this.status = status;
	    }

	   
	    public void setHeartkidNumber(String heartkidNumber) {
			this.heartkidNumber = heartkidNumber;
		}
	    public void setLoginAttempts(int loginAttempts) {
	        this.loginAttempts = loginAttempts;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public int getLoginAttempts() {
	        return loginAttempts;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getStatus() {
	        return status;
	    }

		public String getHeartkidNumber() {
			return heartkidNumber;
		}

		
  
	}