package com.sounds.bvs.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressEmbeddable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="ADDRESS" , nullable=true)
	private String address;

	@Column(name="CITY", length=50, nullable=true)
    private String city;
	
	@Column(name="STATE", length=50, nullable=true)
    private String state;

	@Column(name="COUNTRY", length=25, nullable=true)
    private String country;

	@Column(name="EMAIL_ID", length=50,  nullable=true)
	private String emailId;

	@Column(name="PIN", length=6, nullable=true)
    private String pin;

	@Column(name="PHONE", length=10, nullable=true)
    private String phone;

	@Column(name="MOBILE", nullable=true)
    private String mobile;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
