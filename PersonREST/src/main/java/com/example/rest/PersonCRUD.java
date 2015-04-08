package com.example.rest;

import java.util.Random;

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
	 * Person JSON
{  
   "personId":"1",
   "firstName":"John",
   "lastName":"Smith",
   "age":25,
   "address":{  
      "streetAddress":"21 2nd Street",
      "city":"New York",
      "state":"NY",
      "postalCode":"10021"
   },
   "phoneNumber":[  
      {  
         "type":"home",
         "number":"212 555-1234"
      },
      {  
         "type":"mobile",
         "number":"646 555-4567"
      }
   ]
}
	  
	  Persons JSON
{  
   "persons":[  
      {  
         "personId":"1",
         "firstName":"John",
         "lastName":"Smith",
         "age":25,
         "address":{  
            "streetAddress":"21 2nd Street",
            "city":"New York",
            "state":"NY",
            "postalCode":"10021"
         },
         "phoneNumber":[  
            {  
               "type":"home",
               "number":"212 555-1234"
            },
            {  
               "type":"mobile",
               "number":"646 555-4567"
            }
         ]
      }
   ]
}
	  
	  
	  
	 */

	// declare string array and initialize with values in one step
	// key - values to feed random person generator
	String[] personIdA = { "1", "2", "3", "4" };
	String[] firstNameA = { "John", "Mary", "Brett", "Julie" };
	String[] lastNameA = { "Green", "Blue", "Purple", "Black" };
	String[] ageA = { "32", "43", "18", "67" };
	String[] streetAddressA = { "23 Rotorua St", "18 Melbourne Rd", "1 Italy St", "55 Singapore Rd" };
	String[] cityA = { "Auckland", "Wellington", "Christchurch", "Queenstown" };
	String[] stateA = { "New Zealand", "New Zealand", "New Zealand", "New Zealand" };
	String[] postalCodeA = { "1024", "1003", "1010", "1002" };
	String[] numberHomeA = { "04348544", "0323843432", "092324234", "094433123" };
	String[] numberMobileA = { "022133434", "021832133", "0223786786", "0214343434" };
	
	// array boundaries 
	int min = 0;
	int max = 3;

	@GET
	@Produces("application/json")
	@ApiOperation(value = "Get user details", response = String.class)
	public String getPersons() {

		System.out.println("### In getPersons - GET");

		// create persons JSON
		JSONObject persons = new JSONObject();
		JSONArray personsA = new JSONArray();
		
		// adding person to persons JSON
		for (int y = 0; y < 10; y++) {

			// create a random person based on value catalogue (arrays)
			Random rand = new Random();
			int i = rand.nextInt(max - min + 1) + min;
			
			JSONObject address = new JSONObject();
			address.put("streetAddress", streetAddressA[i]);
			address.put("city", cityA[i]);
			address.put("state", stateA[i]);
			address.put("postalCode", postalCodeA[i]);

			JSONObject phoneNumber1 = new JSONObject();
			phoneNumber1.put("type", "home");
			phoneNumber1.put("number", numberHomeA[i]);

			JSONObject phoneNumber2 = new JSONObject();
			phoneNumber2.put("type", "mobile");
			phoneNumber2.put("number", numberMobileA[i]);

			JSONArray phoneNumber = new JSONArray();
			phoneNumber.add(phoneNumber1);
			phoneNumber.add(phoneNumber2);

			JSONObject person = new JSONObject();
			person.put("personId", personIdA[i]);
			person.put("firstName", firstNameA[i]);
			person.put("lastName", lastNameA[i]);
			person.put("age", ageA[i]);
			person.put("address", address);
			person.put("phoneNumber", phoneNumber);
			
			personsA.add(person);

		}
		
		persons.put("persons" , personsA);
		
		String result = persons.toString();
		
		return result;
		
		
	}

	@Path("{id}")
	@GET
	@ApiOperation(value = "Return person details", response = String.class)
	public Response getPersonById(@PathParam("id") String id) {

		System.out.println("### In getPersonById - GET");

		if (id == null || id.trim().length() == 0) {
			return Response.serverError().entity("ID cannot be blank").build();
		}
		// To-Do .. something to delete the person
		if (id.equalsIgnoreCase("00")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Entity not found for ID: " + id).build();
		}

		// create a random person based on value catalogue (arrays)
		Random rand = new Random();
		int i = rand.nextInt(max - min + 1) + min;
		
		JSONObject address = new JSONObject();
		address.put("streetAddress", streetAddressA[i]);
		address.put("city", cityA[i]);
		address.put("state", stateA[i]);
		address.put("postalCode", postalCodeA[i]);

		JSONObject phoneNumber1 = new JSONObject();
		phoneNumber1.put("type", "home");
		phoneNumber1.put("number", numberHomeA[i]);

		JSONObject phoneNumber2 = new JSONObject();
		phoneNumber2.put("type", "mobile");
		phoneNumber2.put("number", numberMobileA[i]);

		JSONArray phoneNumber = new JSONArray();
		phoneNumber.add(phoneNumber1);
		phoneNumber.add(phoneNumber2);

		JSONObject person = new JSONObject();
		person.put("personId", id);
		person.put("firstName", firstNameA[i]);
		person.put("lastName", lastNameA[i]);
		person.put("age", ageA[i]);
		person.put("address", address);
		person.put("phoneNumber", phoneNumber);

		String result = person.toString();
		return Response.ok(result, MediaType.APPLICATION_JSON).build();

	}

	@Path("{id}")
	@DELETE
	@ApiOperation(value = "Delete person by given id", response = String.class)
	public Response deletePersonById(@PathParam("id") String id) {

		System.out.println("### In deletePersonById - @DELETE");

		if (id == null || id.trim().length() == 0) {
			return Response.serverError().entity("ID cannot be blank").build();
		}
		// To-Do .. something to delete the person
		if (id.equalsIgnoreCase("00")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Entity not found for ID: " + id).build();
		}
		String json = "{\"message\": \"Entity " + id + " deleted\"}";
		return Response.ok(json, MediaType.APPLICATION_JSON).build();

	}

	// @Path("{c}")
	// @GET
	// @Produces("application/xml")
	// public String convertCtoFfromInput(@PathParam("c") Double c) {
	// Double fahrenheit;
	// Double celsius = c;
	// fahrenheit = ((celsius * 9) / 5) + 32;
	//
	// String result =
	// "@Produces(\"application/xml\") Output: \n\nC to F Converter Output: \n\n"
	// + fahrenheit;
	// return "<ctofservice>" + "<celsius>" + celsius + "</celsius>" +
	// "<ctofoutput>" + result + "</ctofoutput>" + "</ctofservice>";
	// }

}
