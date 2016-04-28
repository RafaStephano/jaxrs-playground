package br.com.rstephano.bundles;

import java.util.Locale;

import javax.validation.MessageInterpolator;

public class LocaleSpecificMessageInterpolator implements MessageInterpolator {
	private final MessageInterpolator defaultInterpolator;
	private final Locale defaultLocale;

	public LocaleSpecificMessageInterpolator(MessageInterpolator interpolator, Locale locale) {
		this.defaultInterpolator = interpolator;
		this.defaultLocale = locale;
	}

	@Override
	public String interpolate(String message, Context context) {
		return defaultInterpolator.interpolate(message, context, this.defaultLocale);
	}

	@Override
	public String interpolate(String message, Context context, Locale locale) {
		return defaultInterpolator.interpolate(message, context, locale);
	}
}
