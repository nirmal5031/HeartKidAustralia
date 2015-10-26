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
public class RegisterProfileEntity {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	  @Column(name = "USERTYPE")
	private String usertype;   
	  @Column(name = "FIRSTNAME")
	private String firstname; 
	  @Column(name = "LASTNAME")
	private String lastname;
	  
	  public RegisterProfileEntity() {
	    }

	    /**
	     * Constructor for entity class RegisterProfileEntity
	     * 
	     * @param usertype
	     * @param firstname
	     * @param lastname
	     */
	    public RegisterProfileEntity(final long id, final String usertype, final String firstname,
	            final String lastname) {
	    	this.id = id;
	        this.usertype = usertype;
	        this.firstname = firstname;
	        this.lastname = lastname;
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
	 @Override
	    public boolean equals(final Object obj) {
	        return Pojomatic.equals(this, obj);
	    }

	    @Override
	    public int hashCode() {
	        return Pojomatic.hashCode(this);
	    }

}
