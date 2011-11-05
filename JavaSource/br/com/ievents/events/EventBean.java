package br.com.ievents.events;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.persistence.EntityManager;

import br.com.ievents.user.User;
import br.com.ievents.util.AbstractManagedBean;

/**
 * 
 * @author ewerttonbravo
 *
 */

@ManagedBean
@SessionScoped
public class EventBean extends AbstractManagedBean {
	
	private Event event;
	
	private HtmlInputText nameInputText;
	private HtmlInputTextarea textInputTextarea;
	
	private EventDao eventDao;
	
	@ManagedProperty(value="#{entityManager}")
	private EntityManager entityManager;
	
	@ManagedProperty(value="#{currentUser}")
	private User currentUser;
	
	public EventBean() {
		nameInputText = new HtmlInputText();
		nameInputText.setRequired(true);
		textInputTextarea = new HtmlInputTextarea();
	}
	
	@PostConstruct
	public void init() {
		eventDao = new EventDao(entityManager);
	}
	
	public String newEvent() {
		event = new Event();
		return "new";
	}
	
	public String saveEvent() {
		try {
			event.setName(nameInputText.getValue().toString());
			event.setText(textInputTextarea.getValue().toString());
			
			currentUser.getEvents().add(event);
			event.setUser(currentUser);
			
			eventDao.save(event);
			addInfoMessage("Evento cadastrado com sucesso");
			return "show";
		} catch (Exception e) {
			addErrorMessage(
					"Erro ao tentar salvar este evento, tente novamente mais tarde...");
			e.printStackTrace();
			return null;
		}
	}

	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public HtmlInputText getNameInputText() {
		return nameInputText;
	}
	public void setNameInputText(HtmlInputText nameInputText) {
		this.nameInputText = nameInputText;
	}
	
	public HtmlInputTextarea getTextInputTextarea() {
		return textInputTextarea;
	}
	public void setTextInputTextarea(HtmlInputTextarea textInputTextarea) {
		this.textInputTextarea = textInputTextarea;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Event> getEvents() {
		return new ArrayList<Event>(currentUser.getEvents());
	}
}
