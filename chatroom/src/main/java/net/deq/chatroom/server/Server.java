package net.deq.chatroom.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import net.deq.chatroom.SocketPack;

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
					} else if (key.isReadable()) {
						SocketPack pack = readFromChannel((SocketChannel) key.channel());
						switch(pack.what) {
						case SocketPack.Action.REGISTER: 
						case SocketPack.Action.SEND: 
						case SocketPack.Action.LEAVE:
						}
					}
				}
				events.remove();
			}
		}
	}

	public void stop() {this.flag = false;}

	private SocketPack readFromChannel(SocketChannel channel) {
		SocketPack result = null;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		try (ByteArrayOutputStream baos = 
				new ByteArrayOutputStream()) {
			int len = -1;
			while ((len = channel.read(buffer)) > 0) {
				buffer.flip();
				baos.write(buffer.array(), 0, len);
			}
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
			try {
				result = (SocketPack) ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} 
		return result;
	}
}
