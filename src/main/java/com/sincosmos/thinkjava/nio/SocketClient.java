package com.sincosmos.thinkjava.nio;

public class SocketClient {
	private static String DEFAULT_HOST = "127.0.0.1";
	private static int DEFAULT_PORT = 8080;
	private static ClientHandler clientHandler;
	public static void start() {
		start(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	private static synchronized void start(String ip, int port) {
		if(clientHandler != null) {
			clientHandler.stop();
		}
		clientHandler = new ClientHandler(ip, port);
		new Thread(clientHandler, "Client").start();
	}
	
	public static boolean sendMsg(String msg) throws Exception {
		if("q".equals(msg)) return false;
		clientHandler.sendMsg(msg);
		return true;
	}
	
	public static void main(String[] args) {
		start();
	}
}
