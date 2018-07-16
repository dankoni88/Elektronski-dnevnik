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
import com.itkpreobuka.Elektronski_dnevnik.entities.GradeEntity;
import com.itkpreobuka.Elektronski_dnevnik.repositories.GradeRepository;

@RestController
@RequestMapping(path = "/eMarkBook/grade")
public class GradeController {

	@Autowired
	private GradeRepository gradeRep;

	// list of all grades
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllGrades() {
		try {
			return new ResponseEntity<Iterable<GradeEntity>>(gradeRep.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// finding grade by id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		if (gradeRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<GradeEntity>(gradeRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Grade with provided ID not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	// adding new grade
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createGrade(@Valid @RequestBody GradeEntity newGrade, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		try {
			GradeEntity grade = new GradeEntity();
			grade.setValue(newGrade.getValue());
			gradeRep.save(grade);
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);
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
	public ResponseEntity<?> deleteGrade(@PathVariable String id) {

		if (!gradeRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Grade with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			GradeEntity grade = gradeRep.findById(Integer.parseInt(id)).get();
			gradeRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// changing grade data
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<?> updateGrade(@Valid @RequestBody GradeEntity newGrade, @PathVariable String id) {

		if (!gradeRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Grade with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			GradeEntity grade = gradeRep.findById(Integer.parseInt(id)).get();
			grade.setValue(newGrade.getValue());
			gradeRep.save(grade);
			return new ResponseEntity<GradeEntity>(grade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
