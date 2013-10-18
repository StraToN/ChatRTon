package org.chatrton.gui.localisation;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Localisator {
	
	private static volatile Localisator instance = null;
	private static ResourceBundle i18ln;
	private static Locale locale;
	
	private Localisator() {
		locale = Locale.getDefault();
		i18ln = ResourceBundle.getBundle("org.chatrton.lang", locale);
	}
	
	private final static Localisator getInstance() {
		if (instance == null) {
			synchronized (Localisator.class) {
				if (instance == null)
					instance = new Localisator();
			}
		}
		return instance; 
	}
	
	public static void changeLocale(Locale newLocale) {
		if (newLocale != locale)
			locale = newLocale;
		i18ln = ResourceBundle.getBundle("org.chatrton.lang", locale);
	}
	
	public static String getString(String key) {
		getInstance();
		return i18ln.getString(key);
	}
}
