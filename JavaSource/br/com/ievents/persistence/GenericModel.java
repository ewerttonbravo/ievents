package br.com.ievents.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericModel<T, K> {
	private Logger logger;
	
	private EntityManager entityManager;
	
	@SuppressWarnings("rawtypes")
	protected Class _class;
	
	@SuppressWarnings("rawtypes")
	protected GenericModel() {
		//	this.entityManager = JPAUtil.getEntityManager();
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();  
		this._class = (Class) thisType.getActualTypeArguments()[0];
		this.logger = LoggerFactory.getLogger(this._class);
	}
	
	private void initEntityManager() {
		if (null == entityManager)
			this.entityManager = JPAUtil.getEntityManager();
	}
	
	public void closeConnection() {
		this.entityManager.close();
	}
	
	public void save() throws Exception {
		try {
			initEntityManager();
			entityManager.persist(this);
		} catch (Exception ex) {
			logger.error("Erro ao tentar salvar: " + this, ex);
			throw new Exception();
		}
	}
	
	public void update() throws Exception  {
		try {
			initEntityManager();
			entityManager.merge(this);
		} catch (Exception ex) { 	
			logger.error("Erro ao tentar atualizar: " + this, ex);
			throw new Exception();
		}
	}

	public void delete() throws Exception  {
		try {
			initEntityManager();
			entityManager.remove(this);
		} catch (Exception ex) {
			logger.error("Erro ao tentar excluir: " + this, ex);
			throw new Exception();
		}
	}

	@SuppressWarnings("unchecked")
	public T find(K id) {  
		initEntityManager();
        return ((T) entityManager.find(_class, id));  
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findByField(String fieldName, Object value) {
		initEntityManager();
		Query query = entityManager.createNamedQuery("find"+_class.getSimpleName()+"By"+fieldName);
		query.setParameter(fieldName.toLowerCase(), value);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked") 
	public List<T> findAll() {
		initEntityManager();
		return entityManager.createNamedQuery("findAll"+_class.getSimpleName()).getResultList();
	}
}
