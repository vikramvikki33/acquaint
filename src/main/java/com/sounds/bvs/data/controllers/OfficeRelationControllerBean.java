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
import com.sounds.bvs.data.dao.OfficeRelationDao;
import com.sounds.bvs.data.dao.impl.OfficeRelationShipImpl;
import com.sounds.bvs.data.domain.OfficeRelationShipEntity;
import com.sounds.bvs.data.utils.constants.GenericConstansts;

@ManagedBean(name="officeCtrlBean")
@SessionScoped
public class OfficeRelationControllerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(OfficeRelationControllerBean.class.getName());
	private OfficeRelationShipEntity officeRelationShip;
	private OfficeRelationDao officeRelationDao;
	private String navigateTo;
	private String loadValidValues;
	private boolean readOnce;
	private boolean disableId;
	private List<OfficeRelationShipEntity> officeRelationList;

	public OfficeRelationControllerBean() {
		if (officeRelationShip == null)
			officeRelationShip = new OfficeRelationShipEntity();
		if (officeRelationDao == null)
			officeRelationDao = new OfficeRelationShipImpl();
	}

	public String getLoadValidValues() {
		readPersonalRelationShip();
		return loadValidValues;
	}

	private void readPersonalRelationShip() {
		if (!readOnce) {
			this.officeRelationDao.setEntityManger(JPAUtil.getEnityManager());
			officeRelationList = this.officeRelationDao.findAll();
			this.officeRelationDao.closeEntityManager();
			readOnce = true;
		}
	}

	public void save(ActionEvent ae) {
		try {
			if (this.officeRelationShip != null) {
				this.officeRelationDao.setEntityManger(JPAUtil.getEnityManager());
				logger.info("the personal relation ship id is " + this.officeRelationShip.getId());
				this.officeRelationDao.update(this.officeRelationShip);
				GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
						this.officeRelationShip.getDescription() + " saved successfully.");
				this.officeRelationList = this.officeRelationDao.findAll();
				this.officeRelationDao.closeEntityManager();
			}
		} catch (Exception e) {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.FATAL_MESSAGE, e.getMessage());
			this.officeRelationDao.closeEntityManager();
		}
	}

	public void findById(AjaxBehaviorEvent abe) {
		this.officeRelationShip = (OfficeRelationShipEntity) GenericBeanHelper
				.getUIComponent(this.officeRelationShip, abe);
		if (this.officeRelationShip != null) {
			this.officeRelationDao.setEntityManger(JPAUtil.getEnityManager());
			this.officeRelationShip = this.officeRelationDao.findById(this.officeRelationShip.getId());
			this.officeRelationDao.closeEntityManager();
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
		this.officeRelationShip.setId(null);
		this.officeRelationShip.setDescription(null);
		disableId = false;
	}

	public void addRelation(ActionEvent ae) {
		this.officeRelationShip = new OfficeRelationShipEntity();
		disableId =false;
	}

	public String navigateTo() {
		return navigateTo;
	}

	public void setLoadValidValues(String loadValidValues) {
		this.loadValidValues = loadValidValues;
	}

	public OfficeRelationShipEntity getOfficeRelationShip() {
		return officeRelationShip;
	}

	public void setOfficeRelationShip(OfficeRelationShipEntity officeRelationShip) {
		this.officeRelationShip = officeRelationShip;
	}

	public List<OfficeRelationShipEntity> getOfficeRelationList() {
		return officeRelationList;
	}

	public void setOfficeRelationList(List<OfficeRelationShipEntity> officeRelationList) {
		this.officeRelationList = officeRelationList;
	}

	public boolean isDisableId() {
		return disableId;
	}

	public void setDisableId(boolean disableId) {
		this.disableId = disableId;
	}
}
