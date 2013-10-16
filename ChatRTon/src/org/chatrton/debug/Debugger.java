package org.chatrton.debug;

public class Debugger {
	public static final boolean isEnabled = true;
	
	public static void log(Object o) {
		if (isEnabled)
			System.out.println("DEBUG: " + o.toString());
	}
}
