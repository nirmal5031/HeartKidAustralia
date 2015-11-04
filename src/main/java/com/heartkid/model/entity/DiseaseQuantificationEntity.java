package com.heartkid.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.pojomatic.annotations.AutoProperty;

@Entity
@Table(name="heartkidregistration")
@AutoProperty
public class DiseaseQuantificationEntity {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String conditioncalld;                    
	private String heartconds;   
	@Column(name="surgeryHeld")
	private String surgeryHeld;                       
	private String surgerydelay;
	@Column(name="Surgerydelaycount")
	private String surgerydelaycount;          
	private String trvlsurg; 
	
	public DiseaseQuantificationEntity()
	{
		
	}
	
	public DiseaseQuantificationEntity(String conditioncalld,String heartconds,String surgeryHeld  ,String surgerydelay,String surgerydelaycount,String trvlsurg)
	{
		super();
		  this.conditioncalld=  conditioncalld;             
		  this.heartconds =    heartconds;             
		  this.surgeryHeld =    surgeryHeld;            
		  this.surgerydelay=surgerydelay;                
		  this.surgerydelaycount  =surgerydelaycount;
		  this.trvlsurg=trvlsurg;        
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConditioncalld() {
		return conditioncalld;
	}

	public void setConditioncalld(String conditioncalld) {
		this.conditioncalld = conditioncalld;
	}

	public String getHeartconds() {
		return heartconds;
	}

	public void setHeartconds(String heartconds) {
		this.heartconds = heartconds;
	}

	public String getSurgeryHeld() {
		return surgeryHeld;
	}

	public void setSurgeryHeld(String surgeryHeld) {
		this.surgeryHeld = surgeryHeld;
	}

	public String getSurgerydelay() {
		return surgerydelay;
	}

	public void setSurgerydelay(String surgerydelay) {
		this.surgerydelay = surgerydelay;
	}

	

	public String getSurgerydelaycount() {
		return surgerydelaycount;
	}

	public void setSurgerydelaycount(String surgerydelaycount) {
		this.surgerydelaycount = surgerydelaycount;
	}

	public String getTrvlsurg() {
		return trvlsurg;
	}

	public void setTrvlsurg(String trvlsurg) {
		this.trvlsurg = trvlsurg;
	}
	
	
}
