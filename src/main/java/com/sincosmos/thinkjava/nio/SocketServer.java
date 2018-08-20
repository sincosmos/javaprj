package com.sincosmos.thinkjava.nio;

public class SocketServer {
	private static int DEFAULT_PORT = 8080;
	private static ServerHandler serverHandler;
	public static void start() {
		start(DEFAULT_PORT);
	}
	
	private static synchronized void start(int port) {
		if(serverHandler != null)
			serverHandler.stop();
		serverHandler = new ServerHandler(port);
		new Thread(serverHandler, "Server").start();
	}
	
	public static void main(String[] args){  
        start();  
    }  
}
