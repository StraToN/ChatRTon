package com.chatrton.architecture;

import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;

import com.chatrton.connection.ActiveMQConnection;

public class Server extends BrokerService {
	private ActiveMQConnection activemqConn;
	private Session session;
	private Connection connection;
	private final String hostname;
	private final int port;
	private ArrayList<String> listChannels;
	private String nickNameServer;
	
	//private HiddenStreamThread hiddenStreamThread;
	
	public Server(String hostname, int port) {
		activemqConn = new ActiveMQConnection(hostname, port);
		this.hostname = hostname;
		this.port = port;
	}
	
	public void connect(String nick) {
		try {
			connection = activemqConn.getActivemqConn().createConnection();
			connection.start();
			setNickNameServer(nick);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			if (connection != null) {
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
	
}
