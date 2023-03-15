package com.yugesh.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

import com.yugesh.springboot.model.Employee;
import com.yugesh.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping(value="/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getEmployeeList() {
		return employeeRepository.findAll();
	}
	
	//create employee rest API
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		Employee emp = employeeRepository.save(employee);
		return emp;
	}
	
	//get employee by Id rest API
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws Exception {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(()-> new Exception("Employee not found with id: " + id));
		return ResponseEntity.ok(emp);
	}
	
	
	//Update employee Rest API
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws Exception {
		Employee getEmp = employeeRepository.findById(id)
				.orElseThrow(() -> new Exception("Employee not found with id: " + id));
		getEmp.setFirstName(employee.getFirstName());
		getEmp.setLastName(employee.getLastName());
		getEmp.setMailId(employee.getMailId());
		getEmp.setDescription(employee.getDescription());
		Employee updateEmp = employeeRepository.save(getEmp);
//		Employee emp = employeeRepository.save(employee);
//		return emp;
		
		return ResponseEntity.ok(updateEmp);
	}
	
	// Delete employee rest API
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws Exception {
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new Exception("Employee not exist with id :" + id));
			
			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
	
}
