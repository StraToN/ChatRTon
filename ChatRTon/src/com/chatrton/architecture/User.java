package com.chatrton.architecture;

import java.util.HashMap;


public class User {
	private String nickName;
	private HashMap<String, Server> listServersConnected; // serverName, Server
	private HashMap<Server, Channel> listChannelsConnected;
	private HashMap<Server, PrivateDiscussion> listPrivateDiscussions;
	
	public User(){
		listServersConnected = new HashMap<String, Server>();
		listChannelsConnected = new HashMap<Server, Channel>();
		listPrivateDiscussions = new HashMap<Server, PrivateDiscussion>();
	}
	
	public User(String nick){
		listServersConnected = new HashMap<String, Server>();
		nickName = nick;
	}
	
	public void connectToServer(String hostname, int port) {
		Server newServer = new Server(hostname, port);
		listServersConnected.put(hostname, newServer);
		newServer.connect(nickName);
	}
	
	public void connectToChannel(String server, String channelName) {
		Server serv = listServersConnected.get(server);
		listChannelsConnected.put(serv, serv.getChannel(channelName));
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
