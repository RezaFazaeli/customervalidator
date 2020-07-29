# Customer Validator
This a sample java project for validating customers requests and showing the statistics of the customer. It took 6 hours to complete.

## the pre-requisites
* You have to install Java 8.
* You have to install Maven 3.

## Technologies: 
* Spring Boot
* Spring Data JPA
* H2
* Swagger ui
* Spring Test
* Spring MVC


### Run test methods:
```
Use "mvn clean package" to run the tests with H2 DB.
```

### Run in development envirenment:
To run the project with **spring-boot:run** in development environment.
In development enviroment you will be able to make more instances according the number of request 
if you can config spring cloud(Eureka) in this project and make a instance from its Eureka server.
```
This sample uses a embedded H2 database and you do not need to install and config a database.
After running you can test and see API(s) on a browser by this URL http://localhost:8080/swagger-ui.html.  
```

### Run in production environment:
Use java -jar file with the below command:  
**java -jar -Dspring.profiles.active=prod jarName.jar**

