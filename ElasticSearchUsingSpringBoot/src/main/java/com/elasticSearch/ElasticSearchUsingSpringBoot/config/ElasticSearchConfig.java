package com.elasticSearch.ElasticSearchUsingSpringBoot.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages="com.elasticSearch.ElasticSearchUsingSpringBoot.Repository")
public class ElasticSearchConfig {
	
	@Value("${elasticsearch.host}")
	private String ElasticSearchHost;
	
	@Value("${elasticsearch.port}")
	private int ElasticSearchPort;
	
	@Value("${elasticsearch.clustername}")
	private String ElasticSearchClusterName;
	
	@Bean
	public Client client() throws UnknownHostException {
		Settings elasticSearchSetting = Settings.
				builder().
				put("cluster.name", ElasticSearchClusterName)
				.build();
		TransportClient client = new PreBuiltTransportClient(elasticSearchSetting)
				.addTransportAddress(new TransportAddress(InetAddress.getByName(ElasticSearchHost), ElasticSearchPort));
		
		return client;

	}
	
	/*@Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }*/



}