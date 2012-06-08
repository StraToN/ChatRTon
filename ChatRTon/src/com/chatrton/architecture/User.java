package com.chatrton.architecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;


public class User {
	private String nickName;
	private HashMap<String, Server> 			listServersConnected; // serverName, Server
	private HashMap<Server, List<Channel> > 	listChannelsConnected;
	private HashMap<Server, PrivateDiscussion> 	listPrivateDiscussions;
	
	public User(){
		listServersConnected = new HashMap<String, Server>();
		listChannelsConnected = new HashMap<Server, List<Channel> >();
		listPrivateDiscussions = new HashMap<Server, PrivateDiscussion>();
	}
	
	public User(String nick){
		listServersConnected = new HashMap<String, Server>();
		listChannelsConnected = new HashMap<Server, List<Channel> >();
		listPrivateDiscussions = new HashMap<Server, PrivateDiscussion>();
		nickName = nick;
	}
	
	public void connectServer(String hostname, int port) {
		Server server = listServersConnected.get(hostname);
		if (server == null)
			server = new Server(hostname, port);
		if (server.connect())
			listServersConnected.put(hostname, server);
	}
	
	public void joinChannel(String hostname, String channelName) throws JMSException {
		Channel chan = getConnectedChannel(hostname, channelName);
		if (chan == null)
		{
			Server server = listServersConnected.get(hostname);
			try {
				chan = server.joinChannel(channelName, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (chan != null) {
				List<Channel> listChannels = listChannelsConnected.get(server);
				if (listChannels == null) {
					listChannels = new ArrayList<Channel>();
					listChannels.add(chan);
					listChannelsConnected.put(server, listChannels);
				}
				else {
					listChannels.add(chan);
				}
				
			}
		}
	}
	
	private Server getConnectedServer(String hostname) {
		return listServersConnected.get(hostname);
	}
	
	private Channel getConnectedChannel(String hostname, String channelName) {
		Server server = listServersConnected.get(hostname);
		List<Channel> listChannel = listChannelsConnected.get(server);
		Channel c = null;
		if (listChannel != null) {
			for (Channel channel : listChannel) {
				if (channel.getChannelName() == channelName) {
					c = channel;
					break;
				}
			}
		}
		return c;
	}

	public void say(String serverName, String channelName, String message) throws JMSException {
		Server server = listServersConnected.get(serverName);
		List<Channel> listChannels = listChannelsConnected.get(server);
		boolean channelFound = false;
		Channel c = null;
		for (Channel channel : listChannels) {
			if (channel.getChannelName() == channelName) {
				channelFound = true;
				c = channel;
				break;
			}
		}
		if (channelFound) {
			c.sendMessage(message, this);
		}
	}
	
	public void disconnect(String hostname) {
		this.getConnectedServer(hostname).disconnect();
	}
	
	
	
	public void addNewChannelConnected(Server server, Channel channel) {
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
}
