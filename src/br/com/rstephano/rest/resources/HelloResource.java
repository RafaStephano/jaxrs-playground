package br.com.rstephano.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.rstephano.rest.exceptions.WebException;

@Path("hello")
public class HelloResource {

	@Context
	HttpServletRequest request;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		throw new WebException("TESTE", 400, request);
	}

}
