package br.com.rstephano.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import br.com.rstephano.bundles.LocaleThreadLocal;

@Provider
public class AcceptLanguageRequestFilter implements ContainerRequestFilter {
	@Context
	private HttpHeaders headers;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LocaleThreadLocal.set(headers.getAcceptableLanguages().get(0));
	}
}
