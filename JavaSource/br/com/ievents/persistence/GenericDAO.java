package br.com.ievents.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericDAO<T, K> {
	private Logger logger;
	
	private EntityManager entityManager;
	
	@SuppressWarnings("rawtypes")
	protected Class _class;
	
	@SuppressWarnings("rawtypes")
	protected GenericDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();  
		this._class = (Class) thisType.getActualTypeArguments()[0];
		this.logger = LoggerFactory.getLogger(this._class);
	}
	
	public void closeConnection() {
		this.entityManager.close();
	}
	
	public void save(T obj) throws Exception {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(obj);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			logger.error("Erro ao tentar salvar: " + obj, ex);
			throw new Exception();
		}
	}
	
	public void update(T obj) throws Exception  {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(obj);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			logger.error("Erro ao tentar atualizar: " + obj, ex);
			throw new Exception();
		}
	}

	public void delete(T obj) throws Exception  {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(obj);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			entityManager.getTransaction().rollback();
			logger.error("Erro ao tentar excluir: " + obj, ex);
			throw new Exception();
		}
	}

	@SuppressWarnings("unchecked")
	public T find(K id) {  
        return ((T)entityManager.find(_class, id));  
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findByField(String fieldName, Object value) {
		Query query = entityManager.createNamedQuery("find"+_class.getSimpleName()+"By"+fieldName);
		query.setParameter(fieldName.toLowerCase(), value);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked") 
	public List<T> findAll() {
		return entityManager.createNamedQuery("findAll"+_class.getSimpleName()).getResultList();
	}
	
	
	
}
