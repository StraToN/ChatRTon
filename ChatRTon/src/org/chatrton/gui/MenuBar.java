package org.chatrton.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.chatrton.debug.Debugger;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -5569257246506350543L;
	private HashSet<JMenu> menuList;
	
	public MenuBar() {
		super();
		menuList = new HashSet<JMenu>();
		
		/*
		Enumeration<String> keysList = i18nRes.getKeys();
		
		while (keysList.hasMoreElements()) {
			String key = keysList.nextElement();
			System.out.println("\n"+key);
			
			String[] tokens = key.split("_");
			
			if (tokens[0].compareTo("menubar") == 0)
			{
				int numLevels = tokens.length - 1;
				System.out.println(numLevels);
				
				if (numLevels > 0)
					System.out.println(tokens[1]);
				if (numLevels > 1)
					System.out.println(tokens[2]);
			}
						
//			if (level == 1) {
//				menuList.add(new JMenu(i18nRes.getString(key)));
//			}
//			else {
//				System.err.println("TODO");
//			}
		}
		*/
	
		JMenu menuFile = new JMenu(Localisator.getInstance().getString("menubar_File"));
		JMenuItem jmiFileConnect = new JMenuItem(Localisator.getInstance().getString("menubar_File_Connect"));
		jmiFileConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				menuFileConnectActionPerformed(evt);
			}
		});
		
		JMenuItem jmiFileExit = new JMenuItem(Localisator.getInstance().getString("menubar_File_Exit"));
		jmiFileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				menuFileExitActionPerformed(evt);
			}
		});
		menuFile.add(jmiFileConnect);
		menuFile.add(jmiFileExit);
		
		// HELP
		JMenu menuAbout = new JMenu(Localisator.getInstance().getString("menubar_Help"));
		JMenuItem jmiHelpAbout = new JMenuItem(Localisator.getInstance().getString("menubar_Help_About"));
		jmiHelpAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				menuHelpAboutActionPerformed(evt);
			}
		});
		menuAbout.add(jmiHelpAbout);
		
		menuList.add(menuAbout);
		menuList.add(menuFile);
		
		
		
		for (JMenu menu : menuList) {
			this.add(menu);
		}
		
	}
	
	
	/**
	 * File/Exit action to open Connection dialog
	 * @param evt
	 */
	private static void menuFileConnectActionPerformed(ActionEvent evt)
	{
		Debugger.log("File/Connect action.");
		ConnectionDialog cd = ConnectionDialog.getInstance();
	}
	
	
	/**
	 * File/Exit action to close the window and finish application
	 * @param evt
	 */
	private static void menuFileExitActionPerformed(ActionEvent evt)
	{
		Debugger.log("File/Exit action.");
		System.exit(0);
	}
	
	/**
	 * Help/About action to show about frame
	 * @param evt
	 */
	private static void menuHelpAboutActionPerformed(ActionEvent evt) {
		Debugger.log("Help/About action.");
	}
	
}
