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
import com.sounds.bvs.data.dao.ClientsDao;
import com.sounds.bvs.data.dao.impl.BranchOfficeImpl;
import com.sounds.bvs.data.dao.impl.ClientsImpl;
import com.sounds.bvs.data.domain.BranchOfficeEntity;
import com.sounds.bvs.data.domain.ClientsEntity;
import com.sounds.bvs.data.utils.constants.GenericConstansts;

@ManagedBean(name = "clientsCtrlBean")
@SessionScoped
public class ClientsControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ClientsControllerBean.class.getName());
	private ClientsEntity clients;
	private ClientsDao clientsDao;
	private String navigateTo;
	private String loadValidValues;
	private boolean readOnce;
	private boolean disableId;
	private List<ClientsEntity> clientsList;

	private List<BranchOfficeEntity> branchList;
	private BranchOfficeDao branchOfficeDao;
	
	public ClientsControllerBean() {
		logger.info("In Construcor ....");
		if (clients == null)
			clients = new ClientsEntity();
		if (clientsDao == null)
			clientsDao = new ClientsImpl();
		if (branchOfficeDao == null)
			branchOfficeDao = new BranchOfficeImpl();
		if(this.clients.getBranchOffice() == null) 
			this.clients.setBranchOffice(new BranchOfficeEntity());
	}

	public String getLoadValidValues() {
		readBranchDetails();
		return loadValidValues;
	}

	private void readBranchDetails() {
		if (!readOnce) {
			this.branchOfficeDao.setEntityManger(JPAUtil.getEnityManager());
			branchList = this.branchOfficeDao.findAll();
			this.branchOfficeDao.closeEntityManager();
			readOnce = true;
		}
	}

	public void readPersonalRelationShip(AjaxBehaviorEvent abe) {
		this.clientsDao.setEntityManger(JPAUtil.getEnityManager());
		clientsList = this.clientsDao.findByClients(this.clients.getBranchOffice().getId());
		this.clientsDao.closeEntityManager();
	}

	public void save(ActionEvent ae) {
		try {
			if (this.clients != null) {
				this.branchOfficeDao.setEntityManger(JPAUtil.getEnityManager());
				this.clients.setBranchOffice(this.branchOfficeDao.findById(this.clients.getBranchOffice().getId()));
				this.branchOfficeDao.closeEntityManager();
				logger.info(this.clients.getBranchOffice());
				this.clientsDao.setEntityManger(JPAUtil.getEnityManager());
				logger.info("the BranchOffice and CLients id is " + this.clients.getId() + this.clients.getBranchOffice().getId());
				if (this.clients.getId() == null) {
					this.clientsDao.save(this.clients);
					GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
							this.clients.getClientName() + " saved successfully.");
				} else {
					this.clientsDao.update(this.clients);
					GenericBeanHelper.showGrowlMessages(GenericConstansts.INFO_MESSAGE,
							this.clients.getClientName() + " Updated successfully.");
				}
				this.clientsList = this.clientsDao.findAll();
				this.clientsDao.closeEntityManager();
			}
		} catch (Exception e) {
			GenericBeanHelper.showGrowlMessages(GenericConstansts.FATAL_MESSAGE, e.getMessage());
			this.clientsDao.closeEntityManager();
			e.printStackTrace();
		}
		
	}

	public void findById(AjaxBehaviorEvent abe) {
		logger.info("in FindById method .....");
		this.clients = (ClientsEntity) GenericBeanHelper.getUIComponent(this.clients, abe);
		if (this.clients != null) {
			this.clientsDao.setEntityManger(JPAUtil.getEnityManager());
			this.clients = this.clientsDao.findById(this.clients.getId());
			this.clientsDao.closeEntityManager();
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
		this.clients.setId(null);
		this.clients.setClientName("");
		this.clients.setAddress("");
		this.clients.setMobileNumber(0);
		this.clients.setEmailId("");
		this.clients.setPhoneNumber("");
		disableId = false;
	}

	public void addRelation(ActionEvent ae) {
		this.clients = new ClientsEntity();
		disableId = false;
	}

	public String navigateTo() {
		return navigateTo;
	}

	public ClientsEntity getClients() {
		return clients;
	}

	public void setClients(ClientsEntity clients) {
		this.clients = clients;
	}

	public List<ClientsEntity> getClientsList() {
		return clientsList;
	}

	public void setClientsList(List<ClientsEntity> clientsList) {
		this.clientsList = clientsList;
	}

	public void setLoadValidValues(String loadValidValues) {
		this.loadValidValues = loadValidValues;
	}

	public boolean isDisableId() {
		return disableId;
	}

	public void setDisableId(boolean disableId) {
		this.disableId = disableId;
	}

	public List<BranchOfficeEntity> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BranchOfficeEntity> branchList) {
		this.branchList = branchList;
	}
}
