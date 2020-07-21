package com.elasticSearch.ElasticSearchUsingSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ElasticSearchUsingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchUsingSpringBootApplication.class, args);
	}
}
