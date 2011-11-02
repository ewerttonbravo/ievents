package br.com.ievents.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * 
 * @author ewerttonbravo
 *
 */
public abstract class AbstractManagedBean {
	public FacesContext getCurrentFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ExternalContext getExternalContext() {
		if (getCurrentFacesContext() != null)
			return getCurrentFacesContext().getExternalContext();
		return null;
	}

	public Map<String, String> getParameterMap() {
		return getExternalContext().getRequestParameterMap();
	}

	public void addErrorMessage(String msg) {
		getCurrentFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public void addInfoMessage(String msg) {
		getCurrentFacesContext().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}
}
