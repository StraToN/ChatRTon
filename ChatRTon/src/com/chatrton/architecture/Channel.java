package com.chatrton.architecture;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

public class Channel implements MessageListener {
	private String					channelName;
	private ArrayList<User> 		usersList;
	private TopicSession	 		topicSession;
	private TopicPublisher			topicPublisher;
	private TopicSubscriber			topicSubscriber;
	
	private Topic					topic;

	public Channel(String name, User creator, TopicConnection topicConnection) throws JMSException {
		channelName = name;
		usersList = new ArrayList<User>();
		usersList.add(creator);
		
		topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = topicSession.createTopic(channelName);
		topicPublisher = topicSession.createPublisher(topic);
		topicSubscriber = topicSession.createSubscriber(topic);
		
		// the channel receives his messages himself
		topicSubscriber.setMessageListener(this);
	}
	
	public void leave() {
		// TODO : notify the channel that we leave
	}

	public void sendMessage(String string, User user) throws JMSException {
		// TODO : add username into message
		TextMessage message = topicSession.createTextMessage(string);
		topicPublisher.send(message);
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

	@Override
	public void onMessage(Message messageReceived) {
		// TODO : transform message into command and treat it elsewhere
		if (messageReceived instanceof TextMessage) {
			try {
				System.out.println( this.channelName + " : " + ((TextMessage) messageReceived).getText() );
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
