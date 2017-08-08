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
import com.sounds.bvs.data.dao.BranchOfficeDao;
import com.sounds.bvs.data.dao.impl.BranchOfficeImpl;
import com.sounds.bvs.data.domain.AddressEmbeddable;
import com.sounds.bvs.data.domain.BranchOfficeEntity;
import com.sounds.bvs.data.utils.constants.GenericConstansts;

@ManagedBean(name="branchOfficeBean")
@SessionScoped
public class BranchOfficeControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BranchOfficeControllerBean.class.getName());
	private BranchOfficeEntity branchOffice;
	private BranchOfficeDao brachOfficeDao;
	private String navigateTo;
	private String loadValidValues;
	private boolean readOnce;
	private boolean disableId;
	private List<BranchOfficeEntity> branchOfficeList;

	public BranchOfficeControllerBean() {
		if (branchOffice == null)
			branchOffice = new BranchOfficeEntity();
		if (branchOffice.getAddress() == null)
			branchOffice.setAddress(new AddressEmbeddable());
		if (brachOfficeDao == null)
			brachOfficeDao = new BranchOfficeImpl();
	}

	public String getLoadValidValues() {
		readPersonalRelationShip();
		return loadValidValues;
	}

	private void readPersonalRelationShip() {
		if (!readOnce) {
			this.brachOfficeDao.setEntityManger(JPAUtil.getEnityManager());
			branchOfficeList = this.brachOfficeDao.findAll();
			this.brachOfficeDao.closeEntityManager();
			readOnce = true;
		}
	}

	public void save(ActionEvent ae) {
		try {
			if (this.branchOffice != null) {
				this.brachOfficeDao.setEntityManger(JPAUtil.getEnityManager());
				logger.info("the personal relation ship id is " + this.branchOffice.getId());
				if (this.branchOffice.getId() == null) {
					this.brachOfficeDao.save(this.branchOffice);
					GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
							this.branchOffice.getName() + " saved successfully.");
				} else {
					this.brachOfficeDao.update(this.branchOffice);
					GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
							this.branchOffice.getName() + " Updated successfully.");
				}
				this.branchOfficeList = this.brachOfficeDao.findAll();
				this.brachOfficeDao.closeEntityManager();
			}
		} catch (Exception e) {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.FATAL_MESSAGE, e.getMessage());
			this.brachOfficeDao.closeEntityManager();
		}
	}

	public void findById(AjaxBehaviorEvent abe) {
		logger.info("in FindById method ....." );
		this.branchOffice = (BranchOfficeEntity) GenericBeanHelper.getUIComponent(this.branchOffice, abe);
		if (this.branchOffice != null) {
			this.brachOfficeDao.setEntityManger(JPAUtil.getEnityManager());
			this.branchOffice = this.brachOfficeDao.findById(this.branchOffice.getId());
			this.brachOfficeDao.closeEntityManager();
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
		this.branchOffice.setId(null);
		this.branchOffice.setName("");
		this.branchOffice.getAddress().setAddress("");
		this.branchOffice.getAddress().setEmailId("");
		this.branchOffice.getAddress().setCity("");
		this.branchOffice.getAddress().setState("");
		this.branchOffice.getAddress().setCountry("");
		this.branchOffice.getAddress().setPhone("");
		this.branchOffice.getAddress().setMobile("");
		disableId = false;
	}

	public void addRelation(ActionEvent ae) {
		this.branchOffice = new BranchOfficeEntity();
		this.branchOffice.setAddress(new AddressEmbeddable());
		disableId = false;
	}

	public String navigateTo() {
		return navigateTo;
	}

	public void setLoadValidValues(String loadValidValues) {
		this.loadValidValues = loadValidValues;
	}

	public BranchOfficeEntity getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOfficeEntity branchOffice) {
		this.branchOffice = branchOffice;
	}

	public List<BranchOfficeEntity> getBranchOfficeList() {
		return branchOfficeList;
	}

	public void setBranchOfficeList(List<BranchOfficeEntity> branchOfficeList) {
		this.branchOfficeList = branchOfficeList;
	}

	public boolean isDisableId() {
		return disableId;
	}

	public void setDisableId(boolean disableId) {
		this.disableId = disableId;
	}

}
