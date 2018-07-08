package com.itkpreobuka.Elektronski_dnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;

@Entity
//@JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	protected Integer id;
	@Version
	@Column
	protected Integer version;
	@Column(nullable = false, name = "first_name")
	protected String firstName;
	@Column(nullable = false, name = "last_name")
	protected String lastName;
	@Column(nullable = false)
	protected String JMBG;
	@Column(nullable = false, name = "phone_number")
	protected String phoneNumber;
	@Column(nullable = false)
	protected String email;
	@JsonIgnore
	protected String password;
	@Column 
	protected UserRole role;

	public UserEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	

}
