package br.com.rstephano.rest.filters;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;

@Provider
public class ExceptionHandler implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		ContainerResponse res = (ContainerResponse) responseContext;
		if (res.isMappedFromException() && ((String )res.getEntity()).startsWith("WebException: ")) {
			ContainerRequest req = (ContainerRequest) requestContext;
			List<Locale> acceptableLanguages = req.getAcceptableLanguages();
			Locale responseLocale = acceptableLanguages.get(0).getLanguage().trim().equals("*") ? Locale.ROOT : acceptableLanguages.get(0);
			ResourceBundle rb = ResourceBundle.getBundle("br.com.rstephano.bundles.exceptions", responseLocale);
			try {
				res.setEntity(rb.getString(((String) res.getEntity()).replaceAll("WebException: ", "")));
				if (res.getHeaders().get("Content-Language") != null) {
					res.getHeaders().remove("Content-Language");
				}
				if (!rb.getLocale().getLanguage().trim().equals(Locale.ROOT.getLanguage().trim())) {
					res.getHeaders().add("Content-Language", rb.getLocale().getLanguage().toLowerCase() + (rb.getLocale().getCountry() != null && !rb.getLocale().getCountry().trim().equals("") ? "-" + rb.getLocale().getCountry().toLowerCase() : ""));
				}
			} catch (MissingResourceException e) {
				e.printStackTrace();
			}
		}
	}

}
