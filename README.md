# Elastic_Search_SpringBoot

(ELK)Elasticsearch Logstash Kibana

Elastic Search is an Open-source Enterprise REST based Real-time Search and Analytics Engine. Its core Search Functionality is built using Apache Lucene but supports many other features.
It is written in Java Language. It supports Store, Index, Search and Analyse Data in Real-time. Like MongoDB, Elasticsearch is also a Document-based NoSQL Data Store.
Elasticsearch Features: -
•	An Open source
•	Supports Full-text Simple and Powerful Search
•	Supports REST Based API (JSON over HTTP)
•	Supports Real-time Search and Analytics
•	By Definition, Distributed
•	Supports Multi Tenancy Feature
•	Support Cloud and Big Data Environments
•	Supports Cross-platform
•	Denormalized NoSQL Data Store
Advantages or Benefits of Elasticsearch: -
•	An Open source
•	Light Weight with REST API
•	Highly Available. Easily and Highly Scalable
•	Supports Caching Data
•	Schema Free
•	Fast Search Performance
•	Supports both Structured and UN-Structured Data
•	Supports Distributed, Sharding, Replication, Clustering and Multi-Node Architecture
•	Supports Bulk Operations
•	Build Charts and Dashboards within no time

Elasticsearch terminologies: - 
1. What is an Index in Elasticsearch?
In Elasticsearch, an Index is a collection of Documents. For instance, “bookstore” is a Document. Index is used for indexing, searching, updating and deleting Documents. It must be in lower case.
An Index is similar to Database in Relation Database World. Index must be in lower case while declaring.	
2. What is a Type in Elasticsearch?
In Elasticsearch, a Type is a category of similar Documents. That means we can group a set of similar Documents into a Type. As we know in real-world, a “bookstore” contains different kinds of items: a collection of “Books”, a collections Pens, Pencils, CDs etc. In the same way, “bookstore” Document (One kind of Index) can contain a collection of Types: books, pens, CDs etc.
A Type is similar to Table in Relation Database World.
3. What is a Document in Elasticsearch?
In Elasticsearch, a Document is an instance of a Type. It contains Data with Key and Value fairs. For instance, “title”: “Functional Programming In Java” is a Key: Value fair of a Document of Type: “Books”. Each Document has an id.
A Document is similar to a Row in a Table in Relation Database World. Key is Column name and value is Column value.

To store data, we need to have Elasticsearch server up. There are two ways of doing it – 
1.	Installing local elastic search and running the bin/elasticsearch.bat file which will run the elastic server locally.
2.	The other way is to create AWS elastic server, make it as host and connect to it.
Steps to connect to elastic server – 
Create a configuration file in your microservice and configure RestHighLevelClient as a connector that would handle data from elastic server.
private RestHighLevelClient buildClient() 
{
		
restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, PORT, PROTOCOL)));
		
	return restHighLevelClient;
}
•	If we are using local server then put host as localhost otherwise the AWS host path and accordingly corresponding port and protocol.

•	After configuration first step is to load all the data from RDBMS to Elasticsearch DB, which will be only one-time configuration. 

•	Map all the data from RDBMS to Elasticsearch server. For that create an index first and attach this data as source to this index for loading initial data.

•	To create index there is a utility class created in the app-common-fwk service – ElasticsearchUtil.java. All the utility methods like creating index, deleting index and many more are added in that class.

After loading initial data to search data from the elastic server use RestHighLevelClient configured in previous steps to connect. Build search queries accordingly using Querybuilders and add fuzziness if required and use SearchIndex API to search docs. Searching methods are also added in ElasticsearchUtil.java class. Create the necessary query and pass it as argument to util class search function along with index and other required parameters.
Get the response and process data accordingly.

To transfer incremental data from RDBMS to elastic server use Logstash. 
To configure Logstash download 6.4 version of Logstash or the corresponding compatible version according to the Elasticsearch. 
Create a Logstash config file in the path logstash\config\your.config file. 
Give the config file path in logstash\config\pipeline.yml file’s path.config parameter.
In the config file there will be two sections input and output – 
Input section – 
Contains the information regarding the RDBMS settings the queries. Refer below code for more details.
Output section-
Output section would contain details regarding the Elasticsearch server like host, index, doc id and more as shown below.    



.config file – 
input {  
  jdbc {
   	 jdbc_driver_library => "Driver path"
   	 jdbc_driver_class => "Driver class "
    	jdbc_connection_string => "connection string"
  	  jdbc_user => "userid"
   	 jdbc_password => "password"
   	 schedule => "* * * * *" //Configure load time
   	 statement => "SELECT * FROM FIN_MASTER_COMPANY "
	 use_column_value => true
	 tracking_column => Tracking column
	 tracking_column_type => "timestamp"
   	 clean_run => true 
	 record_last_run => true
  }
} 
Output
 {
	stdout { codec => rubydebug 
 }
    elasticsearch {
        	hosts => ["Your host"]
        	index => "INDEX NAME"
       	doc_as_upsert => true
document_id => "doc id”
    } 
}
After configuring run the Logstash server in your local to test and in the production environment we will be using infra server to deploy Logstash server.
Command to run Logstash- 
logstash-6.1.2\bin\logstash.bat -f .\logstash-6.1.2\config\logstash.conf

To get the updated data use UpdateResponse API of Elasticsearch as shown below -
restHighLevelClient.update(updaterequestAPI, RequestOptions.DEFAULT);




