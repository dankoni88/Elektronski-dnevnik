package com.itkpreobuka.Elektronski_dnevnik.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;


// @JsonIgnoreProperties({"hybernateLazyInitializer","handler"})
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	protected Integer id;

	@Version
	@Column
	protected Integer version;

	// @NotNull(message = "First name must be provided.")
	// @Size(min=2, max=30, message = "First name must be between {min} and {max}
	// characters long.")
	@Column(name = "first_name")
	protected String firstName;

	// @NotNull(message = "Last name must be provided.")
	// @Size(min=2, max=30, message = "Last name must be between {min} and {max}
	// characters long.")
	@Column(name = "last_name")
	protected String lastName;

	@Column
	// @NotNull(message = "JMBG must be provided.")
	// @Size(min=13, max=13, message = "JMBG must be 13 characters long.")
	protected String JMBG;

	// @NotNull(message = "Phone number must be provided.")
	// @Pattern(regexp = "^06[0-9] {1}[/]?[0-9] {6-7}$", message="Phone number is
	// not valid. Correct format is 06*/*******.")
	@Column(name = "phone_number")
	protected String phoneNumber;

	@Column
	// @NotNull(message = "Email must be provided.")
	// @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
	// "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	// message="Email is not valid.")
	protected String email;

	// @NotNull(message = "Password must be provided.")
	// @Size(min=5, max=15, message = "Password must be between {min} and {max}
	// characters long.")
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
