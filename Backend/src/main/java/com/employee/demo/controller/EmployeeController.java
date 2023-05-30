package com.employee.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.exception.ResourceNotFoundException;
import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepo;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo repo;
	
	@CrossOrigin
	@GetMapping(value="/employees")
	public List<Employee> getAllEmployees(){
		return repo.findAll();
	}
	
	@CrossOrigin
	@PostMapping(value="/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return repo.save(employee);
	}
	
	@CrossOrigin
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found in database with id :- " + id));
		return ResponseEntity.ok(employee);
	}
	
	@CrossOrigin
	@PutMapping(value="/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		Employee existingEmployee = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found in database with id :- " + id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		Employee updatedEmployee = repo.save(existingEmployee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@CrossOrigin
	@DeleteMapping(value="/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found in database with id :- "+id));
		
		repo.delete(employee);
		Map<String,Boolean> message = new HashMap<>();
		message.put("Deleted Successful!!", Boolean.TRUE);
		return ResponseEntity.ok(message);
	}
	
	
	
	
	
	
}
