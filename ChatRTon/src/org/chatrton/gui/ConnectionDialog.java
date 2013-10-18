package org.chatrton.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.chatrton.debug.Debugger;
import org.chatrton.gui.localisation.Localisator;

public class ConnectionDialog extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7185220667061024452L;
	
	private static volatile ConnectionDialog instance = null;
	
	private JButton jbOK;
	private JButton jbCancel;

	private ConnectionDialog() {
		super();
		this.setTitle(Localisator.getString("frameconnection_Name"));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setContent();
	}
	
	private final static ConnectionDialog getInstance() {
		if (instance == null) {
			synchronized (ConnectionDialog.class) {
				if (instance == null)
					instance = new ConnectionDialog();
			}
		}
		return instance; 
	}
	
	
	private void setContent() {
		JPanel mainPanel = new JPanel(new FlowLayout());
		
		JLabel labelServerURL = new JLabel(Localisator.getString("frameconnection_Label_Server"));
		JTextField jtfServerURL = new JTextField(20);
		
		jbOK = new JButton(Localisator.getString("global_Button_OK"));
		jbOK.addActionListener(new ConnectionDialogController(this));
		
		jbCancel = new JButton(Localisator.getString("global_Button_Cancel"));
		jbCancel.addActionListener(this);
		
		mainPanel.add(labelServerURL);
		mainPanel.add(jtfServerURL);
		mainPanel.add(jbOK);
		mainPanel.add(jbCancel);
		
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public static void showInstance() {
		getInstance().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Debugger.log("Cancel Connection action.");
		if (e.getSource() == this.jbCancel)
			this.dispose();
	}

}
