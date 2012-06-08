package com.chatrton.architecture;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

public class Channel {
	private String					channelName;
	private ArrayList<User> 		usersList;
	private TopicSession	 		topicSession;
	private TopicPublisher			topicPublisher;
	private TopicSubscriber			topicSubscriber;
	
	private ReceiveMessagesThread 	receiveThread;
//	private SendMessagesThread		sendThread;
	Topic							topic;

	public Channel(String name, User creator, TopicConnection topicConnection) throws JMSException {
		channelName = name;
		usersList = new ArrayList<User>();
		usersList.add(creator);
		
		topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = topicSession.createTopic(channelName);
		topicPublisher = topicSession.createPublisher(topic);
		topicSubscriber = topicSession.createSubscriber(topic);
		
		receiveThread = new ReceiveMessagesThread(this);
		receiveThread.start();
	}
	
	public void leave() {
		// todo : notify the channel that we leave
		if (receiveThread.isAlive()) {
			receiveThread.halt();
		}
	}

	public void say(String string) throws JMSException {
		TextMessage message;
		message = topicSession.createTextMessage(string);
		topicPublisher.publish(message);
	}
	
	public Message receive() throws JMSException {
		return topicSubscriber.receive();
	}
	
	
	/**
	 * Gets channel name
	 * @return channel Name
	 */
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
