package net.deq.chatroom;

import java.io.Serializable;

public class SocketPack implements Serializable {
	public String message;
	public String userName;
	public int what;

	public static class Action implements Serializable {
		public static final int SEND = 1; 
		public static final int REGISTER = 2;
		public static final int LEAVE = 3;
	}

}
