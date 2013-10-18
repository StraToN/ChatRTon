package org.chatrton.gui;

import javax.swing.JFrame;

import org.chatrton.gui.localisation.Localisator;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -315891229243487266L;
	private DiscussionPanel discussionPan;
	private MenuBar menuBar;
	
	public MainFrame() {
		super(Localisator.getString("Application_Name"));
		
		discussionPan = new DiscussionPanel();
		this.add(discussionPan);
		menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //this.pack();
		this.setVisible(true);
	}
}
