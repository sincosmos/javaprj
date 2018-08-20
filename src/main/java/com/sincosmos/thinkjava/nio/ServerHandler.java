package com.sincosmos.thinkjava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerHandler implements Runnable{
	private Selector selector;
	private ServerSocketChannel serverChannel;
	private volatile boolean started;

	public ServerHandler(int port) {
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false); //Non-blocking channel
			//backlog port 1024
			serverChannel.socket().bind(new InetSocketAddress(port), 1024);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			started = true;
			System.out.println("Server started. Listening on " + port);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		started = false;
	}

	@Override
	public void run() {
		while(started) {
			try {
				/* Selects a set of keys whose corresponding channels 
				 * are ready for I/O operations
			     */
				selector.select(1000);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iter = keys.iterator();
				SelectionKey key = null;
				while(iter.hasNext()) {
					key = iter.next();
					iter.remove();
					try {
						handleInput(key);
					}catch(Exception e) {
						if(key!=null) {
							key.cancel();
							if(key.channel() != null) {
								key.channel().close();
							}
						}
					}
					
				}
			}catch(Throwable t) {
				t.printStackTrace();
			}
		}
		
		if(selector != null) {
			try {
				selector.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()) {
			if(key.isAcceptable()) {
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				
				SocketChannel sc = ssc.accept();
				
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			
			if(key.isReadable()) {
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				
				int readBytes = sc.read(buffer);
				if(readBytes>0) {
					buffer.flip();
					byte[] bytes = new byte[buffer.remaining()];
					//copy data from buffer to byte array
					buffer.get(bytes);
					String expression = new String(bytes, "UTF-8");
					System.out.print("Message received by server: " + expression);
					String result = null;
					result = "returning: " + expression;
					doWrite(sc, result);
				}else if(readBytes < 0) {
					key.cancel();
					sc.close();
				}
			}
		}
	}

	private void doWrite(SocketChannel sc, String result) throws IOException {
		byte[] bytes = result.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		sc.write(writeBuffer);
	}

}
