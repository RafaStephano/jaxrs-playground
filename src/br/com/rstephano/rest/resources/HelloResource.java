package br.com.rstephano.rest.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.rstephano.db.entities.Conto;
import br.com.rstephano.db.repositories.ContoRepository;
import br.com.rstephano.rest.exceptions.WebException;

@Path("hello")
public class HelloResource {

	@Context
	private HttpServletRequest request;

	private ContoRepository contoRepository;

	public HelloResource() {
		super();
		contoRepository = new ContoRepository();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		Conto conto = new Conto(null, "1", "teste", "Era uma vez...");
		contoRepository.inserir(conto);
		throw new WebException("test", 400, request);
	}

}
