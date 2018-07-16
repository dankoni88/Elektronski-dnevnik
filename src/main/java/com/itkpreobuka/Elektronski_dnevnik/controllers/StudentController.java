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
import com.itkpreobuka.Elektronski_dnevnik.entities.StudentEntity;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.CreateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.UpdateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;
import com.itkpreobuka.Elektronski_dnevnik.repositories.StudentRepository;

@RestController
@RequestMapping(path = "/eMarkBook/student")
public class StudentController {
	@Autowired
	private StudentRepository studentRep;

	// list of all students
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllStudents() {
		try {
			return new ResponseEntity<Iterable<StudentEntity>>(studentRep.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// finding student by id
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		if (studentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<StudentEntity>(studentRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<RESTError>(new RESTError("Student with provided ID not found."),
					HttpStatus.NOT_FOUND);
		}
	}

	// adding new student
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@Valid @RequestBody CreateUserDTO user, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		try {
			StudentEntity student = new StudentEntity();
			student.setEmail(user.getEmail());
			student.setFirstName(user.getFirstName());
			student.setLastName(user.getLastName());
			student.setPassword(user.getEmail());
			student.setRole(UserRole.Student);
			studentRep.save(student);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}

	// deleting student
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable String id) {

		if (!studentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Student with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			StudentEntity student = studentRep.findById(Integer.parseInt(id)).get();
			studentRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	// changing student data
	@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
	public ResponseEntity<?> updateAdmin(@Valid @RequestBody UpdateUserDTO updateStudent, @PathVariable String id) {

		if (!studentRep.findById(Integer.parseInt(id)).isPresent()) {
			return new ResponseEntity<RESTError>(new RESTError("Student with provided ID not found"),
					HttpStatus.NOT_FOUND);
		}
		try {
			StudentEntity student = studentRep.findById(Integer.parseInt(id)).get();
			student.setJMBG(updateStudent.getJMBG());
			;
			student.setPhoneNumber(updateStudent.getPhoneNumber());
			studentRep.save(student);
			return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError("Exception occured: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
