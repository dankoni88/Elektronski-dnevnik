package com.itkpreobuka.Elektronski_dnevnik.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UpdateUserDTO {

	
	@NotNull(message = "JMBG must be provided.")
	@Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9} $", message = "JMBG is not valid. It must contain 13 numbers.")
	private String JMBG;

	@NotNull(message = "Phone number must be provided.")
	//@Pattern(regexp = "^06[0-9]{1}[/]?[0-9]{6-7}$", message = "Phone number is not valid. Correct format is 06*/*******.")
	private String phoneNumber;

	public UpdateUserDTO() {
	}

	
	
	public UpdateUserDTO(
			@NotNull(message = "JMBG must be provided.") @Pattern(regexp = "^[0-9]{13} $", message = "JMBG is not valid. It must contain 13 numbers.") String jMBG,
			@NotNull(message = "Phone number must be provided.") @Pattern(regexp = "^06[0-9]{1}[/]?[0-9]{6-7}$", message = "Phone number is not valid. Correct format is 06*/*******.") String phoneNumber) {
		super();
		JMBG = jMBG;
		this.phoneNumber = phoneNumber;
	}



	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String JMBG) {
		this.JMBG = JMBG;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
