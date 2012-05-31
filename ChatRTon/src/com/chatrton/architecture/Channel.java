package com.chatrton.architecture;

import java.util.ArrayList;

import javax.jms.TopicConnection;

public class Channel {
	private String			channelName;
	private ArrayList<User> usersList;
	private TopicConnection topicConnection;
//	private ReceiveMessagesThread receiveThread;
//	private SendMessagesThread	sendThread;

	public Channel(String name, User creator) {
		channelName = name;
		usersList = new ArrayList<User>();
		usersList.add(creator);
	}
	
	public String getChannelName() {
		return channelName;
	}

	/**
	 * Sets the name of the channel
	 * @param channelName
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}
