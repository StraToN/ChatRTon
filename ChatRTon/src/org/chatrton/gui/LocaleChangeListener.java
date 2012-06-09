package org.chatrton.gui;

/**
 * This interface is implemented by components who need to be updated when the locale is changed
 * When implemented, the function onLocaleChange() states the values to change
 *
 */
public interface LocaleChangeListener {
	void onLocaleChange();
}
