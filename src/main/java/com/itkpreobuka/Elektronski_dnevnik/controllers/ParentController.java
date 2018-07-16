package com.itkpreobuka.Elektronski_dnevnik.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itkpreobuka.Elektronski_dnevnik.controllers.util.RESTError;
import com.itkpreobuka.Elektronski_dnevnik.entities.ParentEntity;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.CreateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.UpdateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;
import com.itkpreobuka.Elektronski_dnevnik.repositories.ParentRepository;

@RestController
@RequestMapping("/eMarkBook/parent")
public class ParentController {

	@Autowired
	private ParentRepository parentRep;

	// list of all parents
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllParents() {
		try {
			return new ResponseEntity<Iterable<ParentEntity>>(parentRep.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// finding parent by id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		if (parentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<ParentEntity>(parentRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Parent with provided ID not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	// adding new parent
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createParent(@Valid @RequestBody CreateUserDTO user, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		try {
			ParentEntity parent = new ParentEntity();
			parent.setEmail(user.getEmail());
			parent.setFirstName(user.getFirstName());
			parent.setLastName(user.getLastName());
			parent.setPassword(user.getEmail());
			parent.setRole(UserRole.Parent);
			parentRep.save(parent);
			return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	// deleting parent
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> deleteParent(@PathVariable String id) {

		if (!parentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Parent with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			ParentEntity parent = parentRep.findById(Integer.parseInt(id)).get();
			parentRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	// changing parent data
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<?> updateParent(@Valid @RequestBody UpdateUserDTO updateParent, @PathVariable String id) {

		if (!parentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Parent with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			ParentEntity parent = parentRep.findById(Integer.parseInt(id)).get();
			parent.setJMBG(updateParent.getJMBG());
			;
			parent.setPhoneNumber(updateParent.getPhoneNumber());
			parentRep.save(parent);
			return new ResponseEntity<ParentEntity>(parent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
