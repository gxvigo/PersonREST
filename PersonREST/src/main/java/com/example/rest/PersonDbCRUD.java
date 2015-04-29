package com.example.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.BSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.example.model.Person;
import com.example.utils.mongo.MongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/db-persons")
@Api(value = "/db-persons", description = "Persons REST for Integration Testing. Data from MongoDB")
public class PersonDbCRUD {
	

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



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve all persons from db", response = String.class)
	public Response getPersons() {

		System.out.println("### Retrieve all persons from db - GET");
		
		// Get a db connection to mongodb. Retrieve the given collection ("persons")
		DBCollection coll = MongoDB.INSTANCE.getSomeDB().getCollection("persons");
		
		// MongoDb - count all the object in a collection
		System.out.println("### number of object in persons collection: " + coll.getCount());
		
		//DBCursor cursor = coll.find(new BasicDBObject());
		DBCursor personsCursor = coll.find();
		
		BasicDBObject persons = new BasicDBObject("persons", personsCursor);
		System.out.println("### persons" + persons.toString());

		Response response = null;
		return response.ok(persons.toString(), MediaType.APPLICATION_JSON).build();
		
	}
	

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add person to db", response = String.class)
    public Response createUser(Person person,
            @DefaultValue("false") @QueryParam("allow-admin") boolean allowAdmin)
            throws URISyntaxException {
    	
    	System.out.println("### In add person - @POST");
    	System.out.println("### person: " + person);
    	
    	// check whether the personId already exist
    	
    	// add person document into mongodb
    	
        System.out.println(person.getPersonId());
        return Response.status(201)
                .contentLocation(new URI("/PersonREST/api/db-persons/" + person.getPersonId())).build();
    }

    
    
/**		

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

**/

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Return person details by personId", response = String.class)
	public Response getPersonById(@PathParam("id") String id) {

		System.out.println("### In deletePersonById - @DELETE");

		if (id == null || id.trim().length() == 0) {
			return Response.serverError().entity("ID cannot be blank").build();
		}
		// To-Do .. something to delete the person
		
		System.out.println("### id: " + id);
		if (id.equalsIgnoreCase("00")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Entity not found for ID: " + id).build();
		}
		
		// Get a db connection to mongodb. Retrieve the given collection ("persons")
		DBCollection coll = MongoDB.INSTANCE.getSomeDB().getCollection("persons");
		
		int id_int = Integer.parseInt(id);
		BasicDBObject query = new BasicDBObject("personId", id_int);
		DBCursor personCursor = coll.find(query);
		System.out.println("### number of items returned by the query: " + personCursor.count());
		
		while(personCursor.hasNext()) {
			
			DBObject currentPerson = personCursor.next();
		    System.out.println("### Document returned based on id" + currentPerson);
		    
			return Response.ok(currentPerson.toString(), MediaType.APPLICATION_JSON).build();		    
		}

		String json = "{\"message\": \"No Entity found\"}";
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}



	@Path("{id}")
	@DELETE
	@ApiOperation(value = "Delete person by given personId", response = String.class)
	public Response deletePersonById(@PathParam("id") String id) {

		System.out.println("### In deletePersonById - @DELETE");

		if (id == null || id.trim().length() == 0) {
			return Response.serverError().entity("ID cannot be blank").build();
		}
		// To-Do .. something to delete the person
		
		System.out.println("### id: " + id);
		if (id.equalsIgnoreCase("00")) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Entity not found for ID: " + id).build();
		}
		
		// Get a db connection to mongodb. Retrieve the given collection ("persons")
		DBCollection coll = MongoDB.INSTANCE.getSomeDB().getCollection("persons");
		
		int id_int = Integer.parseInt(id);
		BasicDBObject query = new BasicDBObject("personId", id_int);
		DBCursor personCursor = coll.find(query);
		System.out.println("### number of items returned by the query: " + personCursor.count());
		
		while(personCursor.hasNext()) {
			
			DBObject currentPerson = personCursor.next();
		    System.out.println("### Document returned based on id" + currentPerson);
		    
		    // Delete object from MondoDB
		    coll.remove(currentPerson);
			
			String json = "{\"message\": \"Entity " + id + " deleted\"}";
			return Response.ok(json, MediaType.APPLICATION_JSON).build();		    
		}

		String json = "{\"message\": \"No Entity deleted\"}";
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
