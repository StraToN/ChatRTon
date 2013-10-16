/**
 * 
 */
package org.chatrton;

import java.util.*;

import javax.jms.JMSException;

import org.chatrton.architecture.ChannelNotConnectedException;
import org.chatrton.architecture.ServerNotConnectedException;
import org.chatrton.architecture.User;
import org.chatrton.gui.MainFrame;





/**
 * @author Julian Murgia
 */
public class Main {

	public static void main(String[] args) {	
		// Init localization
        final ResourceBundle i18ln = ResourceBundle.getBundle("org.chatrton.lang", Locale.getDefault());
        
        MainFrame mainPanel = new MainFrame(i18ln);
	}
	/*
	public static void main(String[] args) {
		User user = new User("StraToN");
		
		try {
			user.connectServer("localhost", 61616);
			user.joinChannel("localhost", "channeltest");
		} catch (JMSException e2) {
			e2.printStackTrace();
		}
		
		
		try {
			user.say("localhost", "channeltest", "RE Coucou channeltest!");
		} catch (JMSException e1) {
			e1.printStackTrace();
		} catch (ServerNotConnectedException e1) {
			e1.showError();
		} catch (ChannelNotConnectedException e1) {
			e1.showError();
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		user.disconnect("localhost");
	}
	*/
}
