package com.heartkid.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class OutHospitalEntity {
	  @Id
	  private long id;
	  private String doctorcountsee;          
	private String traveldistdoc;               
	private String aftrsurgfeel ;               
	private String heardheartkid ;                    
	private String memberheartkid ;                 
	private String supportheartkid ;                 
	private String useheartkid ;                      
	private String desccommentsany ;     
	public OutHospitalEntity(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getDoctorcountsee() {
		return doctorcountsee;
	}
	public void setDoctorcountsee(String doctorcountsee) {
		this.doctorcountsee = doctorcountsee;
	}
	public String getTraveldistdoc() {
		return traveldistdoc;
	}
	public void setTraveldistdoc(String traveldistdoc) {
		this.traveldistdoc = traveldistdoc;
	}
	public String getAftrsurgfeel() {
		return aftrsurgfeel;
	}
	public void setAftrsurgfeel(String aftrsurgfeel) {
		this.aftrsurgfeel = aftrsurgfeel;
	}
	public String getHeardheartkid() {
		return heardheartkid;
	}
	public void setHeardheartkid(String heardheartkid) {
		this.heardheartkid = heardheartkid;
	}
	public String getMemberheartkid() {
		return memberheartkid;
	}
	public void setMemberheartkid(String memberheartkid) {
		this.memberheartkid = memberheartkid;
	}
	public String getSupportheartkid() {
		return supportheartkid;
	}
	public void setSupportheartkid(String supportheartkid) {
		this.supportheartkid = supportheartkid;
	}
	public String getUseheartkid() {
		return useheartkid;
	}
	public void setUseheartkid(String useheartkid) {
		this.useheartkid = useheartkid;
	}
	public String getDesccommentsany() {
		return desccommentsany;
	}
	public void setDesccommentsany(String desccommentsany) {
		this.desccommentsany = desccommentsany;
	}
}
