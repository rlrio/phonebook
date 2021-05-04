# REST API with Spring Boot, PostgreSQL, JPA, JUnit
This repository provides source code of a phonebook web app, allowing CRUD operations on users and contacts via RESTful API.

  
    
## Steps to Setup

**1. Clone this app**
> https://github.com/rlrio/phonebook.git 

**2. Run SQL scripts**
> schema.sql, data.sql

**3. Build and run the app using maven**
> **mvn spring-boot:run**  
   
OR alternatively:  
> **mvn package java -jar target/java -jar phonebook-1.0.jar**

## Explore
  
**!** This app runs on localhost:8081     
  
  **Users**
> GET /users

> GET /users/{userId}
 
> GET /users/search ---- search user by name (or part of the name) 

> POST /users/add

> PUT /users/{userId}

> DELETE /users/{userId}  
  
**Contacts**  

> GET /users/{userId}/contacts

> GET /users/{userId}/contacts/{contactId}

> GET /users/{userId}/contacts/search ---- search contact by phone
  
> POST /users/{userId}/contacts/add 
 
> PUT /users/{userId}/contacts/{contactId}

> DELETE /users/{userId}/contacts/{contactId} 

> DELETE /users/{userId}/contacts 

