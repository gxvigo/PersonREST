package com.example.rest;


import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.utils.mqlight.*;
import com.ibm.mqlight.api.NonBlockingClient;
import com.ibm.mqlight.api.NonBlockingClientAdapter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;



@Path("/memos")
@Api(value = "/memos", description = "Simple api to manage memories")
public class Memos {

	

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Read text from queryParam and create a memo", response = String.class)
    public Response createMemo(@FormParam("text") String text) {
        
        System.out.println("### In Memos.createMemo");
        System.out.println("###text: " + text);
        
        //send memo to the topic 'memos' in mqlight running in localhost
        Mqlight.sendMsg(text);
        
		String msg = "{\"message\": \"Memo created\"}";
		return Response.ok(msg, MediaType.APPLICATION_JSON).build();
    }
	

	
}
