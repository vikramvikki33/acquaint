package com.sounds.bvs.data.controllers;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.sounds.bvs.data.beanhelper.GenericBeanHelper;
import com.sounds.bvs.data.utils.MenuItemUtil;
import com.sounds.bvs.data.utils.constants.GenericConstansts;

@ManagedBean
@SessionScoped
public class NavigationControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuModel menuModel;
	private String suffix = ".xhtml";
	private List<MenuItemUtil> menuItemsList;
	
	public NavigationControllerBean() throws RuntimeException {
		FacesContext context = FacesContext.getCurrentInstance();
		StringBuffer incomingUrl = ((HttpServletRequest)context.getExternalContext().getRequest()).getRequestURL();
		if(incomingUrl != null && incomingUrl.toString().contains("index.xhtml")) {
			int urlWithOutIndex = incomingUrl.lastIndexOf("index.xhtml");
			String requiredUrl = incomingUrl.substring(0, urlWithOutIndex);
			generateMenu(requiredUrl);
		}else {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.FATAL_MESSAGE, "Error in Menu Generation please contact support");
			throw new RuntimeException("Error in Menu Generation");
		}
	}
	
	private void generateMenu(String url) {
		menuModel = new DefaultMenuModel();
		menuItemsList = new LinkedList<>();
		menuItemsList.add(new MenuItemUtil("Personal RelationShip", "fa fa-users", url+  "admin/relationship" + suffix));
		menuItemsList.add(new MenuItemUtil("Office RelationShip", "fa fa-users", url+  "admin/offrelationship" + suffix));
		menuItemsList.add(new MenuItemUtil("Branch Office Managment", "fa fa-users", url+  "admin/branchoffice" + suffix));
		menuItemsList.add(new MenuItemUtil("Clients Managment", "fa fa-users", url+  "admin/clients" + suffix));
		menuModel.addElement(generateMenu("Admin", menuItemsList));
		
	}

	
	private DefaultSubMenu generateMenu(String menuName, List<MenuItemUtil> menuItemList) {
		DefaultSubMenu subMenu = new DefaultSubMenu(menuName);
		for(MenuItemUtil util : menuItemList) {
			subMenu.addElement(generateMenuItem(util));
		}
		return subMenu;
	}
	
	private DefaultMenuItem generateMenuItem(MenuItemUtil util) {
		return new DefaultMenuItem(util.getName(), util.getIcon(), util.getUrl());
	}
	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}
	
	
	
	
}
