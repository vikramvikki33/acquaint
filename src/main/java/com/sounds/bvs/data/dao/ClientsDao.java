package com.sounds.bvs.data.dao;

import java.util.List;

import com.sounds.bvs.data.domain.ClientsEntity;

public interface ClientsDao extends Dao<ClientsEntity, Long>{

	public List<ClientsEntity> findByClients(Long id);
}
