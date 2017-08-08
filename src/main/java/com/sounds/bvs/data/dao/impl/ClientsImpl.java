package com.sounds.bvs.data.dao.impl;

import java.util.List;

import com.sounds.bvs.data.dao.AbstractDao;
import com.sounds.bvs.data.dao.ClientsDao;
import com.sounds.bvs.data.domain.ClientsEntity;

public class ClientsImpl extends AbstractDao<ClientsEntity, Long> implements ClientsDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<ClientsEntity> findByClients(Long id) {

		this.getEntityManager().getTransaction().begin();
		return (List<ClientsEntity>)this.getEntityManager().createQuery("SELECT c FROM ClientsEntity c WHERE c.branchOffice.id = :branchId", ClientsEntity.class)
				.setParameter("branchId", id).getResultList();
	}

}
