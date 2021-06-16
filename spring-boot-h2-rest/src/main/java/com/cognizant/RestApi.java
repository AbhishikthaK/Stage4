package com.cognizant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employees")
public class RestApi {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> storeApi(@Valid @RequestBody Employee emp) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.storeEmployee(emp));	
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllApi() {
		return ResponseEntity.status(HttpStatus.OK).body(service.fetchAllEmployees());
	}
	
	@GetMapping(path = "{eid}")
	public ResponseEntity<Object> getEmployee(@PathVariable("eid") int id) {
		ResponseEntity<Object> response = null;
		try {
			Employee employee = service.fetchEmployee(id);
			response = ResponseEntity.status(200).body(employee);
		} catch (EmployeeNotFoundException e) {
			String err = e.getMessage();
			SimpleResponse sr = new SimpleResponse();
			sr.setMessage(err);
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
		}
		return response;
	}
}
