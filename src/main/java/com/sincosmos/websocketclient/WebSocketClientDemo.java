package com.sincosmos.websocketclient;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClientDemo {
	public static void main(String[] args) {
		try {
			final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://127.0.0.1:5000"));
	
	        // add listener
	        clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
	            public void handleMessage(String message) {
	                System.out.println(message);
	            }
	        });
	        // send message to websocket
	        clientEndPoint.sendMessage("{'event':'addChannel','channel':'client_event'}");
	
	        // wait 5 seconds for messages from websocket
	        Thread.sleep(5000);
	
	    } catch (InterruptedException ex) {
	        System.err.println("InterruptedException exception: " + ex.getMessage());
	    } catch (URISyntaxException ex) {
	        System.err.println("URISyntaxException exception: " + ex.getMessage());
	    }
	}
}
