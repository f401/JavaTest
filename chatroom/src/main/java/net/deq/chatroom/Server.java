package net.deq.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private boolean flag;

	public Server() throws IOException {
		this(8080);
	}

	public Server(int port) throws IOException {
		this.flag = true;
		this.selector = Selector.open();
		this.serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress(port));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void start() throws IOException {
		while (flag) {
			if (selector.selectNow() > 0) {
				Iterator<SelectionKey> events = selector.selectedKeys().iterator();
				while (flag && events.hasNext()) {
					SelectionKey key = events.next();
					if (key.isAcceptable()) {
						SocketChannel socketChannel = serverSocketChannel.accept();
						socketChannel.configureBlocking(false);
						socketChannel.register(selector, SelectionKey.OP_READ);
					} 
				}
				events.remove();
			}
		}
	}

	public void stop() {this.flag = false;}
}
