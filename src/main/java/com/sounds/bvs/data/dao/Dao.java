package com.sounds.bvs.data.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author Vikram
 *
 * @param <T>
 * @param <ID>
 */
public interface Dao<T, ID> extends Serializable {

	public List<T> findAll();
	
	public T findById(ID id);

	public T save(T entity);

	public T update(T entity);

	public void delete(T entity);
	
	public void setEntityManger(EntityManager em);
	
	public EntityManager getEntityManager();
	
	public void closeEntityManager();
	
	public void flush();

	public void clear();
}
