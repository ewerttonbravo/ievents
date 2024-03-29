package br.com.ievents.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.ievents.util.AbstractManagedBean;

/**	
 * 
 * @author ewerttonbravo
 *
 */
@ManagedBean
@SessionScoped
public class UserBean extends AbstractManagedBean {
	
	private List<User> users = new ArrayList<User>();
	private User user;
//	private UserDao userDao;
	
	public UserBean() {}
	
	@PostConstruct
	public void init() {
//		userDao = new UserDao();
		user = new User();
		buscarUsers();
//		User u = user.find(2l);
//		getHttpSession().setAttribute("currentUser", u);
	}
	
	public void buscarUsers() {
		this.users = user.findAll();
	}
	
	/**
	 * Limpar os campos e direcionar para pagina new.xhtml
	 * @return
	 */
	public String newUser() {
		user = new User();
		return "new";
	}
	
	public String saveUser() {
		try {
			addInfoMessage("Usuario Cadastrado com sucesso!");
			user.save();
			
			// Simulando uma autenticacao, com usuario na session
			User u = (User) getHttpSession().getAttribute("currentUser");
			if (null == u)
				getHttpSession().setAttribute("currentUser", user);
			
			buscarUsers();
		} catch (Exception e) {
			addErrorMessage("Houve um problema na operacao, contate o suporte tecnico.");
			return null;
		}
		return "show";
	}
	
	public String editUser() {
		return "edit";
	}
	
	/**
	 * Remove um {@code User} da lista e direciona para index.xhtml
	 * @return
	 */
	public String removeUser() {
		try {
			user.delete();
			buscarUsers();
			user = null;
			addInfoMessage("Usuario removido com sucesso!");
			return "index";
		} catch (Exception e) {
			addErrorMessage("Houve um problema na operacao, contate o suporte tecnico.");
			e.printStackTrace();
			return null;
		}
	}
	
	public String updateUser() {
		try {
			user.update();
			buscarUsers();
			addInfoMessage("Usuario atualizado com sucesso!");
			return "show";
		} catch (Exception e) {
			addErrorMessage("Houve um problema na operacao, contate o suporte tecnico.");
			e.printStackTrace();
			return null;
		}
	}
	
	@PreDestroy
	public void destroy() {
		this.users.clear();
	}
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public void setUsers(List<User> users) { this.users = users; }
	public List<User> getUsers() {
		return users; 
	}
}
