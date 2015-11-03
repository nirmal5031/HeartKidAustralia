package com.heartkid.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Entity
@Table(name="tbl_adminuser_details")
@AutoProperty
public class LoginEntity {

	    @Id
	    @NotNull
	    @Column(name = "USERNAME")
	    private String heartkidNumber;
	    @Column(name = "PASSWORD")
	    private String password;
	    @Column(name = "LOGIN_ATTEMPTS")
	    private String loginAttempts;
	    private String status;
	    private String userrole;
	    private String name;
	    private String address;
	    private String emailid;
	    private String phone;
	    
	    

	    /**
	     * Default constructor for domain class LoginDetail
	     */
	    public LoginEntity() {
	    }

	    /**
	     * Constructor for domain class LoginDetail
	     * 
	    
	     */
	    public LoginEntity(final String heartkidNumber,
	            final String loginAttempts, final String password,
	            final String status, final String name, final String address, final String userrole,final String emailid, final String phone) {
	    
	    	this.heartkidNumber = heartkidNumber;
	        this.loginAttempts = loginAttempts;
	        this.password = password;
	        this.status = status;
	        this.name = name;
	        this.address=address;
	        this.userrole=userrole;
	        this.emailid=emailid;
	        this.phone=phone;
	        
	    }

	    public void setHeartkidNumber(String heartkidNumber) {
			this.heartkidNumber = heartkidNumber;
		}
	    public void setLoginAttempts(String loginAttempts) {
	        this.loginAttempts = loginAttempts;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    public String getLoginAttempts() {
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

		public String getUserrole() {
			return userrole;
		}

		public void setUserrole(String userrole) {
			this.userrole = userrole;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmailid() {
			return emailid;
		}

		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		
  
	}