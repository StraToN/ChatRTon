package com.chatrton.architecture;

import javax.jms.QueueConnection;

public class PrivateDiscussion {
	private QueueConnection queueConn;
	private User destinationUser;
//	private ReceiveMessagesThread receiveThread;
//	private SendMessagesThread	sendThread;

	public PrivateDiscussion(String name, User creator, User destinationUser) {
		this.destinationUser = destinationUser;
		
	}
	

}
