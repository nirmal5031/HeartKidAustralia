package com.heartkid.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;


@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class PersonalInfoEntity {
      @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
      private long id;
	private String referencenumber;
	 @Column(name = "USERTYPE")
	private String usertype;   
	  @Column(name = "FIRSTNAME")
	private String firstname; 
	 @Column(name = "LASTNAME")
	private String lastname;                    
	private String title;                                 
	private String heartkidbirthdate;                 
	private String postcode;                          
	private String state;                               
	private String conctagree;                       
	private String contctviaphone;                  
	private String contctviaemail;                   
	private String phone;                              
	private String email ;                              
	private String ethnicity;                           
	private String countrybirth ;                     
	private String language ;                          
	          
	  
	public PersonalInfoEntity() { 
	  
	  }
	  
	
	
	  public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}

	public String getUsertype() {
		return usertype;
	}



	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getHeartkidbirthdate() {
		return heartkidbirthdate;
	}



	public void setHeartkidbirthdate(String heartkidbirthdate) {
		this.heartkidbirthdate = heartkidbirthdate;
	}



	public String getPostcode() {
		return postcode;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getConctagree() {
		return conctagree;
	}



	public void setConctagree(String conctagree) {
		this.conctagree = conctagree;
	}



	public String getContctviaphone() {
		return contctviaphone;
	}



	public void setContctviaphone(String contctviaphone) {
		this.contctviaphone = contctviaphone;
	}



	public String getContctviaemail() {
		return contctviaemail;
	}



	public void setContctviaemail(String contctviaemail) {
		this.contctviaemail = contctviaemail;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getEthnicity() {
		return ethnicity;
	}



	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}



	public String getCountrybirth() {
		return countrybirth;
	}



	public void setCountrybirth(String countrybirth) {
		this.countrybirth = countrybirth;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}




	
	 public String getReferencenumber() {
			return referencenumber;
		}



		public void setReferencenumber(String referencenumber) {
			this.referencenumber = referencenumber;
		}

		 @Override
		    public boolean equals(final Object obj) {
		        return Pojomatic.equals(this, obj);
		    }

		    @Override
		    public int hashCode() {
		        return Pojomatic.hashCode(this);
		    }

		
	  
	/*  public RegisterPojo(String lname) {
		    this.lname = lname;
		  }*/
	
}
