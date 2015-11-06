package com.heartkid.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class BurdenDiseaseEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;
	private String heartdoc;                     
	private String localdoctorvisit ;                  
	private String emergdeptvisit;                   
	private String careage16 ;                       
	private String childtoadultdoc;                   
	private String anxietycond;                      
	private String anxietycondimpact;
	private String surveystatus;   
	
	public BurdenDiseaseEntity()
	{
		
	}
	
	public String getSurveystatus() {
		return surveystatus;
	}



	public void setSurveystatus(String surveystatus) {
		this.surveystatus = surveystatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeartdoc() {
		return heartdoc;
	}

	public void setHeartdoc(String heartdoc) {
		this.heartdoc = heartdoc;
	}

	public String getLocaldoctorvisit() {
		return localdoctorvisit;
	}

	public void setLocaldoctorvisit(String localdoctorvisit) {
		this.localdoctorvisit = localdoctorvisit;
	}

	public String getEmergdeptvisit() {
		return emergdeptvisit;
	}

	public void setEmergdeptvisit(String emergdeptvisit) {
		this.emergdeptvisit = emergdeptvisit;
	}

	public String getCareage16() {
		return careage16;
	}

	public void setCareage16(String careage16) {
		this.careage16 = careage16;
	}

	public String getChildtoadultdoc() {
		return childtoadultdoc;
	}

	public void setChildtoadultdoc(String childtoadultdoc) {
		this.childtoadultdoc = childtoadultdoc;
	}

	public String getAnxietycond() {
		return anxietycond;
	}

	public void setAnxietycond(String anxietycond) {
		this.anxietycond = anxietycond;
	}

	public String getAnxietycondimpact() {
		return anxietycondimpact;
	}

	public void setAnxietycondimpact(String anxietycondimpact) {
		this.anxietycondimpact = anxietycondimpact;
	}
	
	
	
}
