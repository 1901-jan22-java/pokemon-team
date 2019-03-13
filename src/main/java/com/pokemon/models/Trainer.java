package com.pokemon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Entity
@Table(name="pokemon_trainers")
public class Trainer {
	
	@Id
	@Column(name="TRAINER_ID")
	@SequenceGenerator(name="PT_SEQ_GEN", sequenceName="PT_SEQ", allocationSize=1)
	@GeneratedValue(generator="PT_SEQ_GEN", strategy=GenerationType.SEQUENCE)
	@Min(value=0)
	private int id;
	
	@Column(name="username", nullable=false, unique=true)
	@NotNull(message="username is necessary")
	@Pattern(regexp="[a-z-A-Z]*", message="Only include letters")
	private String username;
	
	@Column(name="password",nullable=false)
	@NotNull(message="Password is necessary")
	private String password;
	
	@Column(name="first_name",nullable=false)
	@NotNull(message="First Name is necessary")
	@Pattern(regexp="[a-z-A-Z]*", message="Only include letters")
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	@NotNull(message="Last Name is necessary")
	@Pattern(regexp="[a-z-A-Z]*", message="Only include letters")
	private String lastName;

	public Trainer() {}
	
	public Trainer(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
	
}
