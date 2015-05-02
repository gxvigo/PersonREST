package com.example.utils.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class initDb {

	public static void resetDB(String dbname, String collectionName){

	    final String MONGO_DB_HOST = "localhost";
	    final int MONGO_DB_PORT = 27017;
	    final String MONGO_DB_NAME = "test";
	    Mongo mongo = null;
	    DB testDB = null;

	        try {
	            mongo = new MongoClient(MONGO_DB_HOST, MONGO_DB_PORT);
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        }

	        testDB = mongo.getDB(MONGO_DB_NAME);
	         //authenticate if needed
	         //boolean auth = someDB.authenticate("username", "password".toCharArray());
	         //if(!auth){
	         //     System.out.println("Error Connecting To DB");
	         //}   
	        
	        
	        /**
	         * 
	         * CLASS TO BE COMPLETED... ACTUALLY NOT STARTED YET!
	         * GOAL: POPULATE MongoDB with an initial collection of persons
	         */
	        
			// Get a db connection to mongodb. Retrieve the given collection ("persons")
			DBCollection coll = testDB.getCollection("weapons");
	        
	        
	    }

		
		

		
	}

	

