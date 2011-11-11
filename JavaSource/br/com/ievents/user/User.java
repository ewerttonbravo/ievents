package br.com.ievents.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.ievents.events.Event;
import br.com.ievents.persistence.GenericModel;
import br.com.ievents.validator.Role;


/**
 * 
 * @author ewerttonbravo
 *
 */
@Entity
@Table(name="usuarios")
@NamedQueries({
	@NamedQuery(name="findAllUser", query="from User")
})
public class User extends GenericModel<User, Long> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="user_name", nullable = false)
	@NotEmpty
	@NotNull
	private String name;
	
	@Column(name="email", nullable = false)
	@NotNull
	@NotEmpty
	@Size(min=3, max=60)
	@Email
	private String email;
	
	@Column(name="password", nullable = false)
	@Size(min=6, max=8)
	@NotEmpty
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Past
	private Date dob;
	
	@Column(name="role", nullable = false)
	@Role
	private String role;
	
	@OneToMany(mappedBy="user")
	private Set<Event> events = new HashSet<Event>();
	
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Set<Event> getEvents() {return events;}
	public void setEvents(Set<Event> events) { this.events = events; }
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
