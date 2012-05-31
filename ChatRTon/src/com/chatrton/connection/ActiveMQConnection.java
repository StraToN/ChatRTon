package com.chatrton.connection;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQConnection {
	private ActiveMQConnectionFactory activemqConn;
	
	public ActiveMQConnection(String hostname, int port) {
		activemqConn = new ActiveMQConnectionFactory("tcp://"+hostname+":"+Integer.toString(port));
	}
 
	// Renvoie la connection au serveur
	public ActiveMQConnectionFactory getActivemqConn() {
		return activemqConn;
	}
}