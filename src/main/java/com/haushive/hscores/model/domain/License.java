package com.haushive.hscores.model.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="license")
public class License {
	
	@Id
	private String licenseKey;
	private String roleId;
	private String clinic;
	private String location;
	private String emailAddress;
	private String pin;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp addDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp expirationDate;
	
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public Timestamp getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}

}
