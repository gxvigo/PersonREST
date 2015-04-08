Woking API created with Jersey - JAX-RS:

  http://localhost:8080/api/hello/a
  
  http://localhost:8080/PersonREST/api/persons
  http://localhost:8080/PersonREST/api/persons/9

Working API documentation created with SWAGGER:
  
  http://localhost:8080/PersonREST/api/swagger.json


SWAGGER UI pointing  http://localhost:8080/api/api-docs


  http://localhost:8080/index.html
  
  
 ** Working with Tomcat 7 ** 
 
 
 
 ** ISSUES:
 
 SOLVED - Eclipse lists errors in swagger-ui.js (missing ";"). To solve that I added the file in the exclusion list 
 of source file in the project (Eclipse stops validating it)
 
 - swagger-ui test fails due a wrong path (2 times http://localhost:8080) and the request/response sample are missing.