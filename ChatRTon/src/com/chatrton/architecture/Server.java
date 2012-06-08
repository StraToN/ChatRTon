package com.chatrton.architecture;

import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;

import com.chatrton.connection.ActiveMQConnection;

public class Server extends BrokerService {
	private ActiveMQConnection activemqConn;
	private Connection connection;
	private TopicConnection topicConnection;
	private QueueConnection queueConnection;
	private Session session;
	
	private final String hostname;
	private final int port;
	private ArrayList<Channel> listChannels;
	private String nickNameServer;
	
	
	public Server(String hostname, int port) {
		activemqConn = new ActiveMQConnection(hostname, port);
		this.hostname = hostname;
		this.port = port;
		this.listChannels = new ArrayList<Channel>();
	}
	
	public void connect(String nick) {
		try {	
			connection = activemqConn.getActivemqConn().createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			topicConnection = activemqConn.getActivemqConn().createTopicConnection();
			queueConnection = activemqConn.getActivemqConn().createQueueConnection();
			
			connection.start();
			topicConnection.start();
			queueConnection.start();
			
			setNickNameServer(nick);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if (connection != null) {
				
				// leave all channels
				for (Channel channel : listChannels) {
					channel.leave();
				}
				
				session.close();
				topicConnection.close(); 
				queueConnection.close();
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ActiveMQDestination> getListChannels() {
		ArrayList<ActiveMQDestination> topics = new ArrayList<ActiveMQDestination>();
		for (ActiveMQDestination dest : this.getDestinations()) {
			if (dest.isTopic())
				topics.add(dest);
		}
		return topics;
	}

	public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public String getNickNameServer() {
		return nickNameServer;
	}

	public void setNickNameServer(String nickNameServer) {
		this.nickNameServer = nickNameServer;
	}
	
	// todo
	public Channel getChannel(String name) {
		return null;
	}
	
	public Channel createChannel(String channelName, User creator) {
		Channel chan = null ;
		try {
			chan = new Channel(channelName, creator, topicConnection);
			this.listChannels.add(chan);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return chan;
	}
	
	
}
