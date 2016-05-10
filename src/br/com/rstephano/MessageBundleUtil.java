package br.com.rstephano;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.ws.rs.core.HttpHeaders;

public class MessageBundleUtil {

	public enum Key {
		VALIDATIONS("br.com.rstephano.bundles.validations"),
		EXCEPTIONS("br.com.rstephano.resources.exceptions");

		private String code;

		private Key(String c) {
			code = c;
		}

		public String getCode() {
			return code;
		}
	}

	public static String getMessage(Key key, List<Locale> acceptableLanguages, String message) {
		Locale locale = acceptableLanguages.get(0);
		ResourceBundle rb = ResourceBundle.getBundle(key.getCode(), locale.getLanguage().trim().equals("*") ? Locale.ROOT : locale);
		try {
			return rb.getString(message);
		} catch (MissingResourceException e) {
			e.printStackTrace();
			return message;
		}
	}

	public static String getMessage(Key key, HttpHeaders headers, String message, String[] args) {
		List<Locale> acceptableLanguages = headers.getAcceptableLanguages();
		String translatedMessage = getMessage(key, acceptableLanguages, message);
		return MessageFormat.format(translatedMessage, args);
	}

}
