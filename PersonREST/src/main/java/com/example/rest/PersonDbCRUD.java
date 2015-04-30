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
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/db-persons")
@Api(value = "/db-persons", description = "Persons REST for Integration Testing. Data from MongoDB")
public class PersonDbCRUD {
	

	
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
	
	

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Add person to db. Pass person.json in the Request body.", response = String.class)
    public Response createPerson(Person person,
            @DefaultValue("false") @QueryParam("allow-admin") boolean allowAdmin)
            throws URISyntaxException {
    	
    	System.out.println("### In add person - @POST");
    	System.out.println("### person: " + person);
    	
      	// check whether the personId already exist
		
		// Get a db connection to mongodb. Retrieve the given collection ("persons")
		DBCollection coll = MongoDB.INSTANCE.getSomeDB().getCollection("persons");
		
    	int id = person.getPersonId();
		
    	BasicDBObject query = new BasicDBObject("personId", id);
		DBCursor personCursor = coll.find(query);
		System.out.println("### number of items returned by the query: " + personCursor.count());
		
		if (personCursor.count() > 0){
	        // Construct+return the response here...
			String msg = "{\"message\": \"Person with personId " + id + " already in the db\"}";
	        return Response.status(409).type("application/json").entity(msg).build();
		}

		// Convert Person object to JSON
		ObjectMapper mapper = new ObjectMapper();
		JsonNode personJson = mapper.valueToTree(person);		
		
		// Convert Person JSON to DBObject (mongodb)
		DBObject personDb = (DBObject) JSON.parse(personJson.toString());
		
		// add person document into mongodb
		coll.insert(personDb);
    		
        return Response.status(201)
                .contentLocation(new URI("/PersonREST/api/db-persons/" + person.getPersonId())).build();
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
