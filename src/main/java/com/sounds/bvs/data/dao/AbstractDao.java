package com.sounds.bvs.data.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Vikram
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractDao<T, ID extends Serializable> implements Dao<T, ID>  {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final Class<T> persistentClass;
	private EntityManager entityManager; 

	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass(){
		return persistentClass;
	}

	@Override
	public T findById(final ID id) {
		this.entityManager.getTransaction().begin();
		return this.entityManager.find(persistentClass, id);
	}

	@Override
	public T save(final T entity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(entity);
		this.entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public T update(final T entity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(entity);
		this.entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cQuery = builder.createQuery(persistentClass);
		Root<T> queryRoot = cQuery.from(persistentClass);
		cQuery.select(queryRoot);
		this.entityManager.getTransaction().begin();
		return this.entityManager.createQuery(cQuery).getResultList();
	}
	
	
	public void setEntityManger(EntityManager em) {
		this.entityManager = em;
	}
	
	public void closeEntityManager() {
		if(this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}
	
	@Override
	public void delete(final T entity) {
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(entity);
		this.entityManager.getTransaction().commit();
		flush();
	}

	@Override
	public void flush() {
		this.entityManager.getTransaction().begin();
		this.entityManager.flush();
	}

	@Override
	public void clear() {
		this.entityManager.getTransaction().begin();
		this.entityManager.clear();
	}

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}
