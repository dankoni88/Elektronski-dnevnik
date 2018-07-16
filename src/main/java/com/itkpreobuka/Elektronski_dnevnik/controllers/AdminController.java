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
import com.itkpreobuka.Elektronski_dnevnik.entities.Admin;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.CreateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.entities.dto.UpdateUserDTO;
import com.itkpreobuka.Elektronski_dnevnik.enums.UserRole;
import com.itkpreobuka.Elektronski_dnevnik.repositories.AdminRepository;

@RestController
@RequestMapping("/eMarkBook/admin")
public class AdminController {

		@Autowired
		private AdminRepository adminRep;
	
		
//		list of all admins
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<?> getAllAdmins() {
			try {
			return new ResponseEntity<Iterable<Admin>>(adminRep.findAll(),HttpStatus.OK);}
			catch(Exception e){return new ResponseEntity<RESTError>(new RESTError("Exception occured:" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		}
		
//		finding admin by id
		@RequestMapping(method = RequestMethod.GET, value="/{id}")
		public ResponseEntity<?> getById(@PathVariable String id) {
			if (adminRep.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<Admin>(adminRep.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<RESTError>(new RESTError("Admin with provided ID not found."),
						HttpStatus.NOT_FOUND);
			}
		}
//	adding new admin	
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<?> createAdmin(@Valid @RequestBody CreateUserDTO user,BindingResult result) {
			if(result.hasErrors()) 
			{return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);}
	try {
		Admin admin = new Admin();
		admin.setEmail(user.getEmail());
		admin.setFirstName(user.getFirstName());
		admin.setLastName(user.getLastName());
		admin.setPassword(user.getEmail());
		admin.setRole(UserRole.Admin);
		adminRep.save(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.OK); 
		}
	catch(Exception e) {
		return new ResponseEntity<RESTError>(new RESTError("Exception occured: "+ e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);} 
		} 
		private String createErrorMessage(BindingResult result) {
			return 
					result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
		}
		
		
//		deleting admin
		@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
		public ResponseEntity<?> deleteAdmin(@PathVariable String id) {

			if (!adminRep.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<RESTError>(new RESTError("Admin with provided ID not found"),
						HttpStatus.NOT_FOUND);
			}
			try {
			Admin admin = adminRep.findById(Integer.parseInt(id)).get();
			adminRep.deleteById(Integer.parseInt(id));
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);}
			catch(Exception e) {
				return new ResponseEntity<RESTError>(new RESTError("Exception occured: "+ e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);} 
				} 
	
		
//		changing admin data
		@RequestMapping(method = RequestMethod.PUT, value = "update/{id}")
		public ResponseEntity<?> updateAdmin(@Valid @RequestBody UpdateUserDTO updateAdmin,@PathVariable String id) {
			
			if (!adminRep.findById(Integer.parseInt(id)).isPresent()) {
				return new ResponseEntity<RESTError>(new RESTError("Admin with provided ID not found"),	HttpStatus.NOT_FOUND);
			}
			try {
			Admin admin = adminRep.findById(Integer.parseInt(id)).get();
			admin.setJMBG(updateAdmin.getJMBG());;
			admin.setPhoneNumber(updateAdmin.getPhoneNumber());
			adminRep.save(admin);
			return new ResponseEntity<Admin>(admin, HttpStatus.OK);
			}
			catch(Exception e)
			{return new ResponseEntity<RESTError>(new RESTError("Exception occured: "+ e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);}
				
		}


}
