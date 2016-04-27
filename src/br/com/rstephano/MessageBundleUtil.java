package br.com.rstephano;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageBundleUtil {

	public enum Key {
		VALIDATIONS("br.com.rstephano.bundles.validations"),
		EXCEPTIONS("br.com.rstephano.bundles.exceptions");

		private String code;

		private Key(String c) {
			code = c;
		}

		public String getCode() {
			return code;
		}
	}

	public static String getMessage(Key key, Enumeration<Locale> enumeration, String message) {
		Locale locale = enumeration.nextElement();
		ResourceBundle rb = ResourceBundle.getBundle(key.getCode(), locale.getLanguage().trim().equals("*") ? Locale.ROOT : locale);
		try {
			return rb.getString(message);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return message;
		}
	}

	public static String getMessage(Key key, Enumeration<Locale> enumeration, String message, String[] args) {
		String translatedMessage = getMessage(key, enumeration, message);
		return MessageFormat.format(translatedMessage, args);
	}

}
