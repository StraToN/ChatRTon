package com.chatrton.architecture;

import java.util.ArrayList;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.Session;
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
	
	
	public Server(String hostname, int port) {
		activemqConn = new ActiveMQConnection(hostname, port);
		this.hostname = hostname;
		this.port = port;
		this.listChannels = new ArrayList<Channel>();
	}
	
	public boolean connect() {
		boolean connectionSuccessful = false;
		try {	
			connection = activemqConn.getActivemqConnectionFactory().createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			topicConnection = activemqConn.getActivemqConnectionFactory().createTopicConnection();
			queueConnection = activemqConn.getActivemqConnectionFactory().createQueueConnection();
			
			connection.start();
			topicConnection.start();
			queueConnection.start();
			connectionSuccessful = true;
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return connectionSuccessful;
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
				
				System.out.println("Disconnected from " + this.hostname + "successfully.");
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

	// todo
	public Channel getChannel(String name) {
		return null;
	}
	
	public Channel joinChannel(String channelName, User creator) throws JMSException {
		for (Channel channel : listChannels) {
			if (channel.getChannelName() == channelName) {
				return channel;
			}
		}
		
		Channel chan = null ;
		chan = new Channel(channelName, creator, topicConnection);
		this.listChannels.add(chan);
		creator.addNewChannelConnected(this, chan);
		
		return chan;
	}
	
	
}
