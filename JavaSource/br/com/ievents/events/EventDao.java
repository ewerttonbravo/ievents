package br.com.ievents.events;

import javax.persistence.EntityManager;

import br.com.ievents.persistence.GenericDAO;

public class EventDao extends GenericDAO<Event, Long> {
	public EventDao(EntityManager em) {
		super(em);
	}
}
