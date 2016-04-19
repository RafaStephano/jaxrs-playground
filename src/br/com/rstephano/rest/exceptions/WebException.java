package br.com.rstephano.rest.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class WebException extends WebApplicationException {

	public WebException(String message, int status, HttpServletRequest request) {
		super(Response.status(status).entity(message).build());
	}

}
