package com.heartkid.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class ProductivityEducationEntity {
	  @Id
	  private long id;
	
	private String curntwork ;                        
	private String worktime ;                         
	private String disabilityben  ;                     
	private String chdlivingimpc ;               
	private String changeimpactchd ;               
	private String chdimpactwork;             
	private String missschooldays;              
	private String eductnchallng ;                   
	private String schoolgrd ;                        
	private String formalassess;
	  @Column(name = "IMPACTQOL")
	private String impactQol;                         
	private String condimpactschl;                  
	private String condimpactschooldesc ;
	
	public ProductivityEducationEntity()
	{
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCurntwork() {
		return curntwork;
	}
	public void setCurntwork(String curntwork) {
		this.curntwork = curntwork;
	}
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getDisabilityben() {
		return disabilityben;
	}
	public void setDisabilityben(String disabilityben) {
		this.disabilityben = disabilityben;
	}
	public String getChdlivingimpc() {
		return chdlivingimpc;
	}
	public void setChdlivingimpc(String chdlivingimpc) {
		this.chdlivingimpc = chdlivingimpc;
	}
	public String getChangeimpactchd() {
		return changeimpactchd;
	}
	public void setChangeimpactchd(String changeimpactchd) {
		this.changeimpactchd = changeimpactchd;
	}
	public String getChdimpactwork() {
		return chdimpactwork;
	}
	public void setChdimpactwork(String chdimpactwork) {
		this.chdimpactwork = chdimpactwork;
	}
	public String getMissschooldays() {
		return missschooldays;
	}
	public void setMissschooldays(String missschooldays) {
		this.missschooldays = missschooldays;
	}
	public String getEductnchallng() {
		return eductnchallng;
	}
	public void setEductnchallng(String eductnchallng) {
		this.eductnchallng = eductnchallng;
	}
	public String getSchoolgrd() {
		return schoolgrd;
	}
	public void setSchoolgrd(String schoolgrd) {
		this.schoolgrd = schoolgrd;
	}
	public String getFormalassess() {
		return formalassess;
	}
	public void setFormalassess(String formalassess) {
		this.formalassess = formalassess;
	}
	public String getImpactQol() {
		return impactQol;
	}
	public void setImpactQol(String impactQol) {
		this.impactQol = impactQol;
	}
	public String getCondimpactschl() {
		return condimpactschl;
	}
	public void setCondimpactschl(String condimpactschl) {
		this.condimpactschl = condimpactschl;
	}
	public String getCondimpactschooldesc() {
		return condimpactschooldesc;
	}
	public void setCondimpactschooldesc(String condimpactschooldesc) {
		this.condimpactschooldesc = condimpactschooldesc;
	} 
	
	
}
