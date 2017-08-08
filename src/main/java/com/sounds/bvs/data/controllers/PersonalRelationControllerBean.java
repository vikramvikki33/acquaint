package com.sounds.bvs.data.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.log4j.Logger;

import com.sounds.bvs.config.JPAUtil;
import com.sounds.bvs.data.beanhelper.GenericBeanHelper;
import com.sounds.bvs.data.dao.PersonalRelationDao;
import com.sounds.bvs.data.dao.impl.PersonalRelationShipImpl;
import com.sounds.bvs.data.domain.PersonalRelationShipEntity;
import com.sounds.bvs.data.utils.constants.GenericConstansts;

@ManagedBean(name = "perRelationBean")
@SessionScoped
public class PersonalRelationControllerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(PersonalRelationControllerBean.class.getName());
	private PersonalRelationShipEntity personalRelationShip;
	private PersonalRelationDao personalRelationDao;
	private String navigateTo;
	private String loadValidValues;
	private boolean readOnce;
	private boolean disableId;
	private List<PersonalRelationShipEntity> personalRelationList;

	public PersonalRelationControllerBean() {
		if (personalRelationShip == null)
			personalRelationShip = new PersonalRelationShipEntity();
		if (personalRelationDao == null)
			personalRelationDao = new PersonalRelationShipImpl();
	}

	public String getLoadValidValues() {
		readPersonalRelationShip();
		return loadValidValues;
	}

	private void readPersonalRelationShip() {
		if (!readOnce) {
			this.personalRelationDao.setEntityManger(JPAUtil.getEnityManager());
			personalRelationList = this.personalRelationDao.findAll();
			this.personalRelationDao.closeEntityManager();
			readOnce = true;
		}
	}

	public void save(ActionEvent ae) {
		try {
			if (this.personalRelationShip != null) {
				this.personalRelationDao.setEntityManger(JPAUtil.getEnityManager());
				logger.info("the personal relation ship id is " + this.personalRelationShip.getId());
				this.personalRelationDao.update(this.personalRelationShip);
				GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
						this.personalRelationShip.getDescription() + " saved successfully.");
				this.personalRelationList = this.personalRelationDao.findAll();
				this.personalRelationDao.closeEntityManager();
			}
		} catch (Exception e) {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.FATAL_MESSAGE, e.getMessage());
			this.personalRelationDao.closeEntityManager();
		}
	}

	public void findById(AjaxBehaviorEvent abe) {
		this.personalRelationShip = (PersonalRelationShipEntity) GenericBeanHelper
				.getUIComponent(this.personalRelationShip, abe);
		if (this.personalRelationShip != null) {
			this.personalRelationDao.setEntityManger(JPAUtil.getEnityManager());
			this.personalRelationShip = this.personalRelationDao.findById(this.personalRelationShip.getId());
			this.personalRelationDao.closeEntityManager();
			disableId = true;
		} else {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.ERROR_MESSAGE,
					"Error in reading Values please report to support");
		}
	}

	public void cancel(ActionEvent ae) {
		refreshPage();
		readOnce = false;
		navigateTo = "/views/dashboard.xhtml";
	}

	private void refreshPage() {
		this.personalRelationShip.setId(null);
		this.personalRelationShip.setDescription(null);
		disableId = false;
	}

	public void addRelation(ActionEvent ae) {
		this.personalRelationShip = new PersonalRelationShipEntity();
		disableId =false;
	}

	public String navigateTo() {
		return navigateTo;
	}

	public void setLoadValidValues(String loadValidValues) {
		this.loadValidValues = loadValidValues;
	}

	public PersonalRelationShipEntity getPersonalRelationShip() {
		return personalRelationShip;
	}

	public void setPersonalRelationShip(PersonalRelationShipEntity personalRelationShip) {
		this.personalRelationShip = personalRelationShip;
	}

	public List<PersonalRelationShipEntity> getPersonalRelationList() {
		return personalRelationList;
	}

	public void setPersonalRelationList(List<PersonalRelationShipEntity> personalRelationList) {
		this.personalRelationList = personalRelationList;
	}

	public boolean isDisableId() {
		return disableId;
	}

	public void setDisableId(boolean disableId) {
		this.disableId = disableId;
	}
}
