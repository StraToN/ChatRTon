package org.chatrton.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ConnectionDialog extends JFrame {
	private static final long serialVersionUID = -7185220667061024452L;
	
	private static volatile ConnectionDialog instance = null;

	private ConnectionDialog() {
		super();
		this.setTitle(Localisator.getInstance().getString("frameconnection_Name"));
		this.setSize(450,250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		setContent();
	}
	
	public final static ConnectionDialog getInstance() {
		if (instance == null) {
			synchronized (ConnectionDialog.class) {
				if (instance == null)
					instance = new ConnectionDialog();
			}
		}
		return instance; 
	}
	
	
	private void setContent() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbConstraints = new GridBagConstraints();
		
		JTextPane mainDiscussionPane = new JTextPane();
		mainDiscussionPane.setBorder(BorderFactory.createLineBorder(Color.black));
		mainDiscussionPane.setEditable(false);
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		this.add(mainDiscussionPane, gbConstraints);
		
		JTextField typeDiscussionField = new JTextField();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		this.add(typeDiscussionField, gbConstraints);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
