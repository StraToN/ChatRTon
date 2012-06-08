package com.chatrton.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQConnection {
	private ActiveMQConnectionFactory activemqConnectionFactory;
	
	public ActiveMQConnection(String hostname, int port) {
		activemqConnectionFactory = new ActiveMQConnectionFactory("tcp://"+hostname+":"+Integer.toString(port));
	}
 
	// Renvoie la connection au serveur
	public ActiveMQConnectionFactory getActivemqConnectionFactory() {
		return activemqConnectionFactory;
	}
}