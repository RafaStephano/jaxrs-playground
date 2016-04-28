package br.com.rstephano.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.rstephano.MessageBundleUtil;

@Path("hello")
public class HelloResource {

	@Context
	private HttpHeaders headers;
	public HelloResource() {
		super();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		Response response = Response.status(Status.CONFLICT).entity(MessageBundleUtil.getMessage(MessageBundleUtil.Key.EXCEPTIONS, headers, "campo_obrigatorio", new String[]{"ffff"})).build();
		throw new WebApplicationException(response);
	}

}
