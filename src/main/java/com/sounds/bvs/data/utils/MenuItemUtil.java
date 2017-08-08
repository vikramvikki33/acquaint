package com.sounds.bvs.data.utils;

import java.io.Serializable;

public class MenuItemUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String icon;
	
	private String url;
	

	public MenuItemUtil() {
		// TODO Auto-generated constructor stub
	}

	public MenuItemUtil(String name, String icon, String url) {
		this.name = name;
		this.icon = icon;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
