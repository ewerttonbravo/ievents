package br.com.ievents.user;

import java.util.ArrayList;
import java.util.List;

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
	private User user = new User();
	private int index = 1;
	
	public UserBean() {}
	
	/**
	 * Limpar os campos e direcionar para pagina new.xhtml
	 * @return
	 */
	public String newUser() {
		user = new User();
		return "new";
	}
	
	public String saveUser() {
		users.add(user);
		user.setId( new Long(index++) );
		addInfoMessage("Usuario Cadastrado com sucesso!");
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
		users.remove(user);
		user = null;
		addInfoMessage("Usuario removido com sucesso!");
		return "index";
	}
	
	public String updateUser() {
		int index = users.indexOf(user);
		users.set(index, user);
		addInfoMessage("Usuario atualizado com sucesso!");
		return "show";
	}
	
	@PreDestroy
	public void destroy() {
		this.users.clear();
	}
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public void setUsers(List<User> users) { this.users = users; }
	public List<User> getUsers() { return users; }
}
