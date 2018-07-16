package com.itkpreobuka.Elektronski_dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itkpreobuka.Elektronski_dnevnik.entities.UserEntity;
import com.itkpreobuka.Elektronski_dnevnik.repositories.UserRepository;

@RestController
@RequestMapping("/eMarkBook/users")
public class UserController {
	
	@Autowired
	private UserRepository userRep;
	
	@RequestMapping(method = RequestMethod.GET)

	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<Iterable<UserEntity>>(userRep.findAll(), HttpStatus.OK);
	}
	


}
