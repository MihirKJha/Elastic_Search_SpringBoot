package com.elasticSearch.ElasticSearchUsingSpringBoot.Service;

import java.util.List;

import com.elasticSearch.ElasticSearchUsingSpringBoot.Employee;

public interface EmployeeService {
	
	void save(Employee employee);
	
	void saveAll(List<Employee> employee);

    void delete(String employeeId);

    Employee findEmployeeById(String id);

    List<Employee> findAll();

    List<Employee> findByName(String name);

 }
