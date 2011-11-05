package br.com.ievents.user;

import javax.persistence.EntityManager;

import br.com.ievents.persistence.GenericDAO;

public class UserDao extends GenericDAO<User, Long> {
	
	public UserDao(EntityManager entityManager) {
		super(entityManager);
	}
}
