package org.chatrton.gui;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -5569257246506350543L;
	private ArrayList<JMenu> menuList;
	
	public MenuBar(ResourceBundle i18nRes) {
		super();
		menuList = new ArrayList<JMenu>();
		
		JMenu menuFile = new JMenu(i18nRes.getString("menubar_File"));
		menuFile.add(new JMenuItem(i18nRes.getString("menubar_File_Connect")));
		menuFile.add(new JMenuItem(i18nRes.getString("menubar_File_Exit")));
		JMenu menuAbout = new JMenu(i18nRes.getString("menubar_Help"));
		menuAbout.add(new JMenuItem(i18nRes.getString("menubar_Help_About")));
		
		menuList.add(menuFile);
		menuList.add(menuAbout);
		
		for (JMenu menu : menuList) {
			this.add(menu);
		}
	}
	
	
}
