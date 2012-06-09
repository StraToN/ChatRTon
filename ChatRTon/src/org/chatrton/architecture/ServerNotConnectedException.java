package org.chatrton.architecture;

public class ServerNotConnectedException extends Exception {
	private static final long serialVersionUID = 1L;
	public String hostName;
	
	public ServerNotConnectedException(String hostName) {
		this.hostName=hostName;
	}
	
	public void showError() {
		System.err.println("Error - server not connected : " + this.hostName );
	}

}
