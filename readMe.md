# Kafka  

Setup kafka : https://dzone.com/articles/running-apache-kafka-on-windows-os

This project contains Event architecture and sending events to Kafka
topics using spring cloud stream.
1) EventSource.Java - Declares Streams
2) EventSourceBinder.Java - Binds the stream to topics
3) Check application.yml for Spring stream related configuration.

	spring:
	  application:
	    name: KafkaTest
	  cloud:
	    stream:
	      kafka:
	        binder:
	          brokers: localhost:9092
	          auto-create-topics: true
	          
	      bindings:
	        output: 
	          destination: outputtopic
	          content-type: application/json 


Dependency required for cloud stream and kafka:

		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-streams</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-stream</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-stream-binder-kafka</artifactId>
		</dependency>

      
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

#For Mongo DB
Step by step setup:

	1. Open mongo shell which is mongo.exe file in bin location
	2. Edit mongod.cfg : uncomment security
		security:
			authorization: "enabled"
		
	3. Use admin
	4. db.createUser(
	{
	user: "admin",
	pwd: "admin",
	roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
	}
	)
	
	5. restart the mongodb in service
	6.  db.auth("admin","admin") in mongo.exe (to check the connection)

Open mongo shell (mongo.exe file in bin)
	1. Create a db
		a. Use dev  --> create a db with name dev
		b. db.createUser(
		{
		user: "devuser",
		pwd: "devuser",
		roles: [ { role: "readWrite", db: "dev" } ]
		}
		)
		
	2. Authenticate
			db.auth("devuser","devuser")
			
Imp Commands:
			
	1. show collections  (collection is identical to table) ---> to see list of collections in a db
	2. show dbs --> to see list of db
	3. Db ---> to see currently selected db
	
	
	
	
	
To connect mongoDb from App
	
	1. Add this in application.yml:
	Spring:
		data:
		    mongodb: 
		      username: devuser
		      password: devuser
		      database: dev
		      port: 27017
		      host: localhost   
	
	2. Add dependency of mongo in pom
	3.  Create repo interface extending MongoRepository<Collection, ID> and further save the data in repo from controller IMpl
	
	
	
Dependency required:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>
	
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

#For SSL/TLS 

Enabling SSL on spring boot app.

1) Setup KeyStore using Keytool cmd
		keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -keystore clientkeystore.jks -validity 3650

*The above command will ask to set up keystore password first and then take the details for the certificate
and then will ask for certificate password.

* Rememeber this is a self signed certificate.

2) Once Keystore is setup and certificate is generated in the keystore, 
   ssl needs to be enabled with spring boot server. 
 2.1) To enable ssl, we will need to provide the location of the keystore,
      the keystore type, the alias name with which the certificate was generated
      and the password to the keystore.  
 2.2) Once done restart your app, and hit the api in the browser using HTTPS.
      The browser will throw a warning because of self signed certs, ignore it and proceed     
      
      	Add this in application.yml
	
	 ssl:
	    key-store-type: JKS
	    key-store: C:\Backup_verizon_end\Northern_Trust\POC\SSL\app\clientkeystore.jks
	    key-store-password: client
	    key-alias: client
	    enabled: true 
      
      
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

#For swagger
Dependency required:

	<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>
		
Add following to main class:

	@EnableSwagger2
	@SpringBootApplication
	public class KafkaTestApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(KafkaTestApplication.class, args);
		}
	
		@Bean
		public Docket productApi() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.basePackage("com.kafka")).build()
					.apiInfo(metaData());
	
		}
	
		private ApiInfo metaData() {
			@SuppressWarnings("deprecation")
			ApiInfo apiInfo = new ApiInfo("Kafka Test Service Api", "Kafka POC - Kafka test Service Api", "1.0",
					"Terms of service", "Kafka", "KafkaTest 1.0", "https://www.dummy.com/");
			return apiInfo;
		}
	}
	
