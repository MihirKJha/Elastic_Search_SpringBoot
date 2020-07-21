package com.elasticSearch.ElasticSearchUsingSpringBoot;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(indexName ="ingrammicro" , type="emolyees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee {
   private String id;
   private String name;
   private String departmentName;
   private String designation;
   private String rolesAndResponsbilty; 
}
