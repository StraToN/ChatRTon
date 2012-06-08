/**
 * 
 */
package com.chatrton;

import javax.jms.JMSException;

import com.chatrton.architecture.Channel;
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
		Channel chan = server.createChannel("chandefou", u);
		
		try {
			chan.say("Coucou !");
			chan.say("Coucou !");
			chan.say("Coucou !");
			chan.say("Coucou !");
			chan.say("Coucou !");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		server.disconnect();
	}

}
