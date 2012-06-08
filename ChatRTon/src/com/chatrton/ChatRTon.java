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
		User user = new User("StraToN");
		
		try {
			user.connectServer("localhost", 61616);
			user.joinChannel("localhost", "chandefou");
			user.joinChannel("localhost", "monbeauchannel");
		} catch (JMSException e2) {
			e2.printStackTrace();
		}
		
		
		try {
			user.say("localhost", "chandefou", "Coucou chandefou!");
			user.say("localhost", "monbeauchannel", "Coucou monbeauchannel!");
			
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		user.disconnect("localhost");
	}

}
