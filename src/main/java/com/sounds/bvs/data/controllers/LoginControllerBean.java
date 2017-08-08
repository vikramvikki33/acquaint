package com.sounds.bvs.data.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class LoginControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String password;
	private String navigateTo;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void login(ActionEvent ae) {
		navigateTo = "dashboard";
	}
	
	public String navigateTo() {
		return navigateTo;
	}
	
	public void logoutUser(ActionEvent ae) {
		
	}
	
}
