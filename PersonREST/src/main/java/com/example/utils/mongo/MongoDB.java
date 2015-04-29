package com.example.utils.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;

public enum MongoDB {
    INSTANCE;

    private static final String MONGO_DB_HOST = "localhost";
    private Mongo mongo;
    private DB testDB;

    MongoDB() {

        try {
            mongo = new MongoClient(MONGO_DB_HOST, 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        testDB = mongo.getDB("test");
         //authenticate if needed
         //boolean auth = someDB.authenticate("username", "password".toCharArray());
         //if(!auth){
         //     System.out.println("Error Connecting To DB");
         //}        
    }

    public DB getSomeDB() {
        return testDB;
    }

    //call it on your shutdown hook for example 
    public void close(){
        mongo.close();
    }
}