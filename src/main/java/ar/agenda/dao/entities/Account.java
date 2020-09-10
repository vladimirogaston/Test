package ar.agenda.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Account {

	@Column(unique = true)
	String username;

	@Column(unique = true)
	String password;
	
	Date initOperations;
	
	Date endOperations;

	@ElementCollection(targetClass = Role.class)
	Set<Role> roles;
}
