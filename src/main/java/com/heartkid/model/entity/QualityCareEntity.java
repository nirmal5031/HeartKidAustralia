package com.heartkid.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class QualityCareEntity {
	  @Id
	  private long id;
	  private String moneyspentinyear;               
	private String frstsurgerysel ;              
	private String hosptlsurgery ;              
	private String educsupporthosp ;           
	private String transpaedtoadult ;          
	private String ratetransition ;             
	private String feelsupport  ;              
	private String heartkidsupport ;                 
	private String socworker ;                       
	private String pstcologist ;                       
	private String familysuprt ;                       
	private String dedicatednurse ;                 
	private String dedicatedCHDnurse ;       
	private String surveystatus;   
	
	public QualityCareEntity(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getSurveystatus() {
		return surveystatus;
	}



	public void setSurveystatus(String surveystatus) {
		this.surveystatus = surveystatus;
	}

	public String getMoneyspentinyear() {
		return moneyspentinyear;
	}

	public void setMoneyspentinyear(String moneyspentinyear) {
		this.moneyspentinyear = moneyspentinyear;
	}

	public String getFrstsurgerysel() {
		return frstsurgerysel;
	}

	public void setFrstsurgerysel(String frstsurgerysel) {
		this.frstsurgerysel = frstsurgerysel;
	}

	public String getHosptlsurgery() {
		return hosptlsurgery;
	}

	public void setHosptlsurgery(String hosptlsurgery) {
		this.hosptlsurgery = hosptlsurgery;
	}

	public String getEducsupporthosp() {
		return educsupporthosp;
	}

	public void setEducsupporthosp(String educsupporthosp) {
		this.educsupporthosp = educsupporthosp;
	}

	public String getTranspaedtoadult() {
		return transpaedtoadult;
	}

	public void setTranspaedtoadult(String transpaedtoadult) {
		this.transpaedtoadult = transpaedtoadult;
	}

	public String getRatetransition() {
		return ratetransition;
	}

	public void setRatetransition(String ratetransition) {
		this.ratetransition = ratetransition;
	}

	public String getFeelsupport() {
		return feelsupport;
	}

	public void setFeelsupport(String feelsupport) {
		this.feelsupport = feelsupport;
	}

	public String getHeartkidsupport() {
		return heartkidsupport;
	}

	public void setHeartkidsupport(String heartkidsupport) {
		this.heartkidsupport = heartkidsupport;
	}

	public String getSocworker() {
		return socworker;
	}

	public void setSocworker(String socworker) {
		this.socworker = socworker;
	}

	public String getPstcologist() {
		return pstcologist;
	}

	public void setPstcologist(String pstcologist) {
		this.pstcologist = pstcologist;
	}

	public String getFamilysuprt() {
		return familysuprt;
	}

	public void setFamilysuprt(String familysuprt) {
		this.familysuprt = familysuprt;
	}

	public String getDedicatednurse() {
		return dedicatednurse;
	}

	public void setDedicatednurse(String dedicatednurse) {
		this.dedicatednurse = dedicatednurse;
	}

	public String getDedicatedCHDnurse() {
		return dedicatedCHDnurse;
	}

	public void setDedicatedCHDnurse(String dedicatedCHDnurse) {
		this.dedicatedCHDnurse = dedicatedCHDnurse;
	}
}
