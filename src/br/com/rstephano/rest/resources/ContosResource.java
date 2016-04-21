package br.com.rstephano.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.rstephano.db.repositories.ContoRepository;
import br.com.rstephano.rest.objects.Conto;

@Path("conto")
public class ContosResource {

	private ContoRepository contoRepository;

	public ContosResource() {
		super();
		contoRepository = new ContoRepository();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response incluir(Conto conto) throws URISyntaxException {
		br.com.rstephano.db.entities.Conto contoDb = new br.com.rstephano.db.entities.Conto(null, conto.getAutorId(), conto.getTitulo(), conto.getConto());
		contoRepository.inserir(contoDb);
		conto.setId(contoDb.getId().toString());
		URI location = UriBuilder.fromPath("conto/{id}").build(contoDb.getId().toString());
		return Response.status(Status.CREATED).entity(conto).location(location).build();
	}
	
}
