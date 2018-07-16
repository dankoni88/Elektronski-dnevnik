package com.itkpreobuka.Elektronski_dnevnik.controllers.util;

import com.fasterxml.jackson.annotation.JsonView;
import com.itkpreobuka.Elektronski_dnevnik.security.Views;

public class RESTError {

	@JsonView(Views.Public.class)
	private String message;
	
	public RESTError(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	
	
}
