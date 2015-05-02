#####################################
#### DEMO of REST WEB SERVICE USING

 - JAX-WS
 - jersey
 - jackson (json java parser)
 - swagger    

## DEMO SETUP ON LOCALHOST #####
## required services:
 - mongodb  : /Users/giovanni/opt/mongodb_3.0.2/bin/startMongo.sh	
 - mqlight  : /Users/giovanni/opt/mqlight-developer-1.0.0.1/mqlight-start
 - bluemix secure connector : start boot2docker: docker run -i -t bluemix/secure-gateway-client ALJT7uIe9QW_prod_ng



## Woking API created with Jersey - JAX-RS:

  # swagger-ui  : http://localhost:8080/PersonREST/#!/memos/createMemo
  # swagger API definition  : http://localhost:8080/PersonREST/api/swagger.json
  
  # Sample implemented in com.example.rest.PersonREST.java (just hello world)
  http://localhost:8080/api/hello/a
  
  # Sample implemented in com.example.rest.PersonCRUD.java (person(s) JSON hardcoded in the java class)
  http://localhost:8080/PersonREST/api/persons
  http://localhost:8080/PersonREST/api/persons/9
  
  # Sample implemented in com.example.rest.PersonDbCRUD.java (person(s) stored in MongoDB in my Mac)
  http://localhost:8080/PersonREST/api/db-persons
  http://localhost:8080/PersonREST/#!/db-persons/getPersons
  
  
  # Sample API Memos  (POST - send the text to a topic running on mqlight, topic used by bluemix service nodered
  http://localhost:8080/PersonREST/api/memos




SWAGGER UI pointing  http://localhost:8080/api/api-docs


  http://localhost:8080/index.html
  
  
 ** Working with Tomcat 7 ** 
 
 
 
 ** ISSUES:
 
 SOLVED - Eclipse lists errors in swagger-ui.js (missing ";"). To solve that I added the file in the exclusion list 
 of source file in the project (Eclipse stops validating it)
 
 - swagger-ui test fails due a wrong path (2 times http://localhost:8080) and the request/response sample are missing.