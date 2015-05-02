package com.example.utils.mqlight;

import com.ibm.mqlight.api.NonBlockingClient;
import com.ibm.mqlight.api.NonBlockingClientAdapter;

public class Mqlight {
	
    public static String sendMsg(final String msg) {
    	
    	final String HOST_NAME = "localhost";
    	
        NonBlockingClient.create("amqp://" + HOST_NAME, new NonBlockingClientAdapter<Void>() {
        	
            public void onStarted(NonBlockingClient client, Void context) {
                client.send("memos", msg, null);
            }
        }, null);
        
		return "Message sent";
    }

}
