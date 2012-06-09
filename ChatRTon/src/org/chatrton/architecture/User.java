package org.chatrton.architecture;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;


public class User {
	private String nickName;
	private HashMap<String, Server> 			listServersConnected; // serverName, Server
	private HashMap<Server, List<Channel> > 	listChannelsConnected;
	private HashMap<Server, PrivateDiscussion> 	listPrivateDiscussions;
	
	/**
	 * Constructor of User
	 */
	public User(){
		listServersConnected = new HashMap<String, Server>();
		listChannelsConnected = new HashMap<Server, List<Channel> >();
		listPrivateDiscussions = new HashMap<Server, PrivateDiscussion>();
	}
	
	/**
	 * Constructor of User, providing nickname
	 * @param nick
	 */
	public User(String nick) {
		listServersConnected = new HashMap<String, Server>();
		listChannelsConnected = new HashMap<Server, List<Channel> >();
		listPrivateDiscussions = new HashMap<Server, PrivateDiscussion>();
		nickName = nick;
	}
	
	/**
	 * Connect to the server of host name  and port provided
	 * @param hostname
	 * @param port
	 */
	public void connectServer(String hostname, int port) {
		Server server = listServersConnected.get(hostname);
		if (server == null)
			server = new Server(hostname, port);
		if (server.connect())
			listServersConnected.put(hostname, server);
	}
	
	/**
	 * Join a channel on the server which name is provided.
	 * @param hostname
	 * @param channelName
	 * @throws JMSException
	 */
	public void joinChannel(String hostname, String channelName) throws JMSException {
		Channel chan = getConnectedChannel(hostname, channelName);
		if (chan == null) // channel not joined yet
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
		//else : channel already joined. Do nothing.
	}
	
	/**
	 * Returns the Server named hostname if we are connected to it, null if not.
	 * @param hostname host name of the server
	 * @return Server connected, null if not
	 */
	private Server getConnectedServer(String hostname) {
		return listServersConnected.get(hostname);
	}
	
	/**
	 * Returns the Channel named channelName if it is connected, else null.
	 * @param hostname host name of the server 
	 * @param channelName channel name
	 * @return Channel corresponding, or null if not connected to the Channel of name provided
	 */
	private Channel getConnectedChannel(String hostname, String channelName) {
		Server server = listServersConnected.get(hostname);
		List<Channel> listChannel = listChannelsConnected.get(server);
		Channel c = null;
		if (listChannel != null) {
			for (Channel channel : listChannel) {
				if (channel.getChannelName().compareTo(channelName) == 0) {
					c = channel;
					break;
				}
			}
		}
		return c;
	}

	/**
	 * Send a string message on the channel channelName on the server serverName
	 * @param serverName host name of the server
	 * @param channelName channem name
	 * @param message string to be sent
	 * @throws JMSException
	 * @throws ServerNotConnectedException
	 * @throws ChannelNotConnectedException
	 */
	public void say(String serverName, String channelName, String message) throws JMSException, ServerNotConnectedException, ChannelNotConnectedException {
		Server server = listServersConnected.get(serverName);
		if (server == null) 
			throw new ServerNotConnectedException(serverName);
		
		List<Channel> listChannels = listChannelsConnected.get(server);
		boolean channelFound = false;
		Channel c = null;
		for (Channel channel : listChannels) {
			if (channel.getChannelName().compareTo(channelName) == 0) {
				channelFound = true;
				c = channel;
				break;
			}
		}
		if (channelFound) {
			c.sendMessage(message, this);
		}
		else {
			throw new ChannelNotConnectedException(channelName);
		}
	}
	
	/**
	 * Leave the server which hostname is the one provided
	 * @param hostname
	 */
	public void disconnect(String hostname) {
		Server server = this.getConnectedServer(hostname);
		
		// leave all the channels we are connected from this server
		for (Channel channel : listChannelsConnected.get(server)) {
			channel.leave();
		}
		
		// finally, leave the server
		if (this.getConnectedServer(hostname).disconnect() == true) {
			this.listServersConnected.remove(hostname);
		}
	}
	
	/**
	 * Returns the current nickname of the user
	 * @return nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Change nickname of the user
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	public void leaveChannel(String hostName, String channelName) throws ServerNotConnectedException, ChannelNotConnectedException {
		Server server = listServersConnected.get(hostName);
		if (server == null) 
			throw new ServerNotConnectedException(hostName);
		
		if (getConnectedChannel(hostName, channelName).leave()) {
			List<Channel> listChannels = listChannelsConnected.get(server);
			boolean channelFound = false;
			Channel c = null;
			for (Channel channel : listChannels) {
				if (channel.getChannelName().compareTo(channelName) == 0) {
					channelFound = true;
					c = channel;
					break;
				}
			}
			if (channelFound) {
				listChannels.remove(c);
			}
			else {
				throw new ChannelNotConnectedException(channelName);
			}
		}
	}

	
}
