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
import com.itkpreobuka.Elektronski_dnevnik.entities.ClassEntity;
import com.itkpreobuka.Elektronski_dnevnik.repositories.ClassRepository;

@RestController
@RequestMapping(path = "/eMarkBook/class")
public class ClassController {

	@Autowired
	private ClassRepository classRep;

	// list of all classes
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllClasses() {
		try {
			return new ResponseEntity<Iterable<ClassEntity>>(classRep.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// finding class by id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		if (classRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<ClassEntity>(classRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Class with provided ID not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	// adding new class
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createAdmin(@Valid @RequestBody ClassEntity newClass, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		try {
			ClassEntity clasS = new ClassEntity();
			clasS.setName(newClass.getName());
			clasS.setGrade(newClass.getGrade());
			classRep.save(clasS);
			return new ResponseEntity<ClassEntity>(clasS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	// deleting class
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> deleteClass(@PathVariable String id) {

		if (!classRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Class with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			ClassEntity clasS = classRep.findById(Integer.parseInt(id)).get();
			classRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<ClassEntity>(clasS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	// changing class data
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<?> updateAdmin(@Valid @RequestBody ClassEntity newClass, @PathVariable String id) {

		if (!classRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Class with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			ClassEntity clasS = classRep.findById(Integer.parseInt(id)).get();
			clasS.setName(newClass.getName());
			;
			clasS.setGrade(newClass.getGrade());
			classRep.save(clasS);
			return new ResponseEntity<ClassEntity>(clasS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
