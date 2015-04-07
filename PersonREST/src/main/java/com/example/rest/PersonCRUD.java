package com.example.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;



@Path("/persons")
@Api(value = "/persons", description = "Persons REST for Integration Testing")
public class PersonCRUD {
	
	/*
	 { 	
	 	"firstName":"John",
   		"lastName":"Smith",
   		"age":25,
   		"address":{ 
   					"streetAddress":"21 2nd Street",
      				"city":"New York",
      				"state":"NY",
      				"postalCode":"10021"
   				},
   		"phoneNumber":[{  
         				"type":"home",
         				"number":"212 555-1234"
      					},
      					{  
         				"type":"mobile",
         				"number":"646 555-4567"
      					}]
	}
	*/
	

		@GET
		@Produces("application/json")
		@ApiOperation(value = "Get user details", response = String.class)
		public String getPersons() {
			
			System.out.println("### In getPersons - GET");
			
			JSONObject address = new JSONObject();
			address.put("streetAddress","21 2nd Street");
			address.put("city","New York");
			address.put("state","NY");
			address.put("postalCode","10021");
			
			JSONObject phoneNumber1 = new JSONObject();
			phoneNumber1.put("type","home");
         	phoneNumber1.put("number","212 555-1234");
      		
         	JSONObject phoneNumber2 = new JSONObject();
      		phoneNumber2.put("type","mobile");
         	phoneNumber2.put("number","646 555-4567");
         	
         	JSONArray phoneNumber = new JSONArray();
         	phoneNumber.add(phoneNumber1);
         	phoneNumber.add(phoneNumber2);
         	
			JSONObject personJ = new JSONObject();
			personJ.put("firstName","John");
			personJ.put("lastName","Smith");
	   		personJ.put("age",25);
	   		personJ.put("address",address);
	   		personJ.put("phoneNumber", phoneNumber);
	 
						
			String result = personJ.toString();
			return result;
		}
		
		
		@Path("{id}")
		@GET
		@ApiOperation(value = "Return person details", response = String.class)
		public Response getPersonById(@PathParam("id") String id) {
			
			System.out.println("### In getPersonById - GET");
			
		    if(id == null || id.trim().length() == 0) {
		        return Response.serverError().entity("ID cannot be blank").build();
		    }
		    // To-Do .. something to delete the person
		    if(id.equalsIgnoreCase("00")) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
		    }
			
			JSONObject address = new JSONObject();
			address.put("streetAddress","21 2nd Street");
			address.put("city","New York");
			address.put("state","NY");
			address.put("postalCode","10021");
			
			JSONObject phoneNumber1 = new JSONObject();
			phoneNumber1.put("type","home");
         	phoneNumber1.put("number","212 555-1234");
      		
         	JSONObject phoneNumber2 = new JSONObject();
      		phoneNumber2.put("type","mobile");
         	phoneNumber2.put("number","646 555-4567");
         	
         	JSONArray phoneNumber = new JSONArray();
         	phoneNumber.add(phoneNumber1);
         	phoneNumber.add(phoneNumber2);
         	
			JSONObject personJ = new JSONObject();
			personJ.put("firstName","John");
			personJ.put("lastName","Smith");
	   		personJ.put("age",25);
	   		personJ.put("address",address);
	   		personJ.put("phoneNumber", phoneNumber);
	 
						
			String result = personJ.toString();
		    return Response.ok(result, MediaType.APPLICATION_JSON).build();		
		  			
		}		
	 
		@Path("{id}")
		@DELETE
		@ApiOperation(value = "Delete person by given id", response = String.class)
		public Response deletePersonById(@PathParam("id") String id) {
			
			System.out.println("### In deletePersonById - @DELETE");
			
		    if(id == null || id.trim().length() == 0) {
		        return Response.serverError().entity("ID cannot be blank").build();
		    }
		    // To-Do .. something to delete the person
		    if(id.equalsIgnoreCase("00")) {
		        return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + id).build();
		    }
		    String json = "{\"message\": \"Entity " + id + " deleted\"}";
		    return Response.ok(json, MediaType.APPLICATION_JSON).build();	
		  			
		}
		
		
//		@Path("{c}")
//		@GET
//		@Produces("application/xml")
//		public String convertCtoFfromInput(@PathParam("c") Double c) {
//			Double fahrenheit;
//			Double celsius = c;
//			fahrenheit = ((celsius * 9) / 5) + 32;
//	 
//			String result = "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n" + fahrenheit;
//			return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" + "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
//		}
	
	
}

