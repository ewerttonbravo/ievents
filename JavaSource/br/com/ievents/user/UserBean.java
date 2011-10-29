package br.com.ievents.user;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 
 * @author ewerttonbravo
 *
 */
@ManagedBean
@SessionScoped
public class UserBean {
	
	private List<User> users = new ArrayList<User>();
	
	public UserBean() {}
	
	
}
