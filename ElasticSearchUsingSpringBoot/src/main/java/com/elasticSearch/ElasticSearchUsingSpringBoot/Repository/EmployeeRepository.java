package com.elasticSearch.ElasticSearchUsingSpringBoot.Repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elasticSearch.ElasticSearchUsingSpringBoot.Employee;

@Repository
public interface EmployeeRepository  extends ElasticsearchRepository<Employee, String>{
  
	List<Employee> findByName(String name);

}
