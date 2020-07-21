package com.elasticSearch.ElasticSearchUsingSpringBoot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elasticSearch.ElasticSearchUsingSpringBoot.Employee;
import com.elasticSearch.ElasticSearchUsingSpringBoot.Service.EmployeeService;

@RestController
public class EmployeeController {
   
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String elasticSearch() {
		return "Welcome to Spring Boot Application using Elastic Search";
	}
	
	@PostMapping("/employees")
	public void saveAllEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);
	}
	
	/*@PutMapping("/employee")
	public String updateEmployee() {
		return "Welcome to Spring Boot Application using Elastis Search";
	}
	*/
	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployee(@PathVariable String employeeId) {
		employeeService.delete(employeeId);
	}
	
	@GetMapping("/employees")
	public List<Employee>  getAllEmployees() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{name}")
	public List<Employee>  findEmployeesByName(@PathVariable String name) {
		return employeeService.findByName(name);
	}
	
	@GetMapping("/employees/find/{id}")
	public Employee findEmployeesById(@PathVariable String id) {
		return employeeService.findEmployeeById(id);
	}
}
