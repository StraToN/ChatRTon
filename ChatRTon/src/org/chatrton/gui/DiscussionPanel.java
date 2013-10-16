package org.chatrton.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;

public class DiscussionPanel extends JPanel {

	private static final long serialVersionUID = 3634937966484184222L;
	protected GridBagConstraints gbConstraints;
	protected JTextPane mainDiscussionPane;
	protected JTextField typeDiscussionField;
	
	public DiscussionPanel() {
		super(new GridBagLayout());
		gbConstraints = new GridBagConstraints();
		
		mainDiscussionPane = new JTextPane();
		mainDiscussionPane.setBorder(BorderFactory.createLineBorder(Color.black));
		mainDiscussionPane.setEditable(false);
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		this.add(mainDiscussionPane, gbConstraints);
		
		typeDiscussionField = new JTextField();
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		this.add(typeDiscussionField, gbConstraints);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
