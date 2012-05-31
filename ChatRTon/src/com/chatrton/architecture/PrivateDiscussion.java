package com.chatrton.architecture;

import javax.jms.QueueConnection;

public class PrivateDiscussion extends Channel {
	private QueueConnection queueConn;
	private User destinationUser;
//	private ReceiveMessagesThread receiveThread;
//	private SendMessagesThread	sendThread;

	public PrivateDiscussion(String name, User creator, User destinationUser) {
		super(name, creator);
		this.destinationUser = destinationUser;
		
	}
	

}
