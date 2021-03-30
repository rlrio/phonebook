# REST API with Spring Boot, H2DB, JPA, JUnit
This repository provides source code of a phonebook web app, allowing CRUD operations on users and contacts via RESTful API.

  
    
## Steps to Setup

**1. Clone this repository**
> https://github.com/rlrio/phonebook  

**2. Build and run the app using maven**
> **mvn spring-boot:run**  
   
OR alternatively:  
> **mvn package java -jar target/java -jar phonebook-1.0.jar**

## Explore

**Users**
> GET /users

> GET /users/{userId}
 
> GET /users/search ---- поиск пользователей по имени (или его части)

> POST /users/add

> PUT /users/{userId}

> DELETE /users/{userId}  
  
**Contacts**  

> GET /users/{userId}/contacts

> GET /users/{userId}/contacts/{contactId}

> GET /users/{userId}/contacts/search ---- поиск телефонной записи по номеру телефона
  
> POST /users/{userId}/contacts/add 
 
> PUT /users/{userId}/contacts/{contactId}

> DELETE /users/{userId}/contacts/{contactId} 

> DELETE /users/{userId}/contacts 

