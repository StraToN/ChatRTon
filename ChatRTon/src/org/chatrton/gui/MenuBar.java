package org.chatrton.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.chatrton.debug.Debugger;
import org.chatrton.gui.localisation.Localisator;

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
	
		JMenu menuFile = new JMenu(Localisator.getString("menubar_File"));
		JMenuItem jmiFileConnect = new JMenuItem(Localisator.getString("menubar_File_Connect"));
		jmiFileConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Debugger.log("File/Connect action.");
				ConnectionDialog.showInstance();
			}
		});
		
		JMenuItem jmiFileExit = new JMenuItem(Localisator.getString("menubar_File_Exit"));
		jmiFileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Debugger.log("File/Exit action.");
				System.exit(0);
			}
		});
		menuFile.add(jmiFileConnect);
		menuFile.add(jmiFileExit);
		
		// HELP
		JMenu menuAbout = new JMenu(Localisator.getString("menubar_Help"));
		JMenuItem jmiHelpAbout = new JMenuItem(Localisator.getString("menubar_Help_About"));
		jmiHelpAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Debugger.log("Help/About action.");
			}
		});
		menuAbout.add(jmiHelpAbout);
		
		menuList.add(menuAbout);
		menuList.add(menuFile);
		
		
		
		for (JMenu menu : menuList) {
			this.add(menu);
		}
		
	}
	
	
	
}
