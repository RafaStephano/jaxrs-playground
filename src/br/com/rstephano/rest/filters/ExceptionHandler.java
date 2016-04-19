package br.com.rstephano.rest.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerResponse;

@Provider
public class ExceptionHandler implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		ContainerResponse cr = (ContainerResponse) responseContext;
		if (cr.isMappedFromException()) {
			System.out.println("Exception");
		}
		responseContext.getEntity();
	}

}
