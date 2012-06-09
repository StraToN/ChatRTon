package org.chatrton.architecture;

public class ChannelNotConnectedException extends Exception {
	private static final long serialVersionUID = 1L;
	public String channelName;

	public ChannelNotConnectedException(String channelName) {
		this.channelName = channelName;
	}
	
	public void showError() {
		System.err.println("Error: channel not connected ("+channelName+")");
	}
}
