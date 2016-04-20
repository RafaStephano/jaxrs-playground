package br.com.rstephano.rest.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class WebException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public WebException(String message, int status, HttpServletRequest request) {
		super(Response.status(status).entity("WebException: " + message).build());
	}

}
