package com.elasticSearch.ElasticSearchUsingSpringBoot.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elasticSearch.ElasticSearchUsingSpringBoot.Employee;
import com.elasticSearch.ElasticSearchUsingSpringBoot.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	@Override
	public void saveAll(List<Employee> employee) {
		employeeRepository.saveAll(employee);
	}


	@Override
	public void delete(String employeeId) {
		Employee employee= findEmployeeById(employeeId);
		employeeRepository.delete(employee);		
	}

	@Override
	public Employee findEmployeeById(String id) {
		Optional<Employee> employee= employeeRepository.findById(id);
		return employee.get() ;
	}

	@Override
	public List<Employee>  findAll() {
		return StreamSupport
				.stream(employeeRepository
						.findAll().spliterator(), false)
				.sorted(Comparator.comparing(Employee::getId))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}
	
}