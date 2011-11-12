package br.com.ievents.auth;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.NonUniqueResultException;

import br.com.ievents.user.User;
import br.com.ievents.util.AbstractManagedBean;

@ManagedBean
@RequestScoped
public class AuthenticationBean extends AbstractManagedBean {

	private String email;
	
	private String password;
	
	private User currentUser;
	
	public AuthenticationBean() {
		currentUser = new User();
	}
	
	public String doLogin() {
		try {
			currentUser = currentUser.findByEmailAndPassword(email, password);
			if (null == currentUser) {
				addErrorMessage("Email/senha invalidos!");
				return null;
			} else {
				getHttpSession().setAttribute("currentUser", currentUser);
				getHttpSession().setAttribute("loggedIn", true);
				return "success";
			}
		} catch (NonUniqueResultException nue) {
			addErrorMessage("Erro contate o suporte");
			nue.printStackTrace();
			return null;
		}
	}
	
	public String doLogout() {
		getHttpSession().setAttribute("currentUser", null);
		getHttpSession().setAttribute("loggedIn", null);
		getHttpSession().invalidate();
		return "/index";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
