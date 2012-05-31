/**
 * 
 */
package com.chatrton;

import com.chatrton.architecture.Server;
import com.chatrton.architecture.User;

/**
 * @author Julian Murgia
 */
public class ChatRTon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new User("StraToN");
		Server server = new Server("localhost", 61616);
		server.connect(u.getNickName());
		
	}

}
