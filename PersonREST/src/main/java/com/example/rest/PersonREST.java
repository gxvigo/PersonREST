package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/hello")
@Api(value = "hello", description = "Say Hello!")
public class PersonREST {

	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Say Hello World", notes = "Anything Else?")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : Hello " + msg;

		return Response.status(200).entity(output).build();

	}

}
