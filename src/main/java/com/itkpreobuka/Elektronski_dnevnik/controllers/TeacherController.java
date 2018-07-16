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
import com.itkpreobuka.Elektronski_dnevnik.entities.TeacherEntity;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.CreateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.UpdateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;
import com.itkpreobuka.Elektronski_dnevnik.repositories.TeacherRepository;

@RestController
@RequestMapping("/eMarkBook/teacher")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRep;

	// list of all teachers
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllTeachers() {
		try {
			return new ResponseEntity<Iterable<TeacherEntity>>(teacherRep.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// finding teacher by id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		if (teacherRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<TeacherEntity>(teacherRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	// adding new teacher
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createTeacher(@Valid @RequestBody CreateUserDTO user, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		try {
			TeacherEntity teacher = new TeacherEntity();
			teacher.setEmail(user.getEmail());
			teacher.setFirstName(user.getFirstName());
			teacher.setLastName(user.getLastName());
			teacher.setPassword(user.getEmail());
			teacher.setRole(UserRole.Teacher);
			teacherRep.save(teacher);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	// deleting teacher
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable String id) {

		if (!teacherRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			TeacherEntity teacher = teacherRep.findById(Integer.parseInt(id)).get();
			teacherRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// changing teacher data
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<?> updateTeacher(@Valid @RequestBody UpdateUserDTO updateTeacher, @PathVariable String id) {

		if (!teacherRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Teacher with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			TeacherEntity teacher = teacherRep.findById(Integer.parseInt(id)).get();
			teacher.setJMBG(updateTeacher.getJMBG());
			;
			teacher.setPhoneNumber(updateTeacher.getPhoneNumber());
			teacherRep.save(teacher);
			return new ResponseEntity<TeacherEntity>(teacher, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
