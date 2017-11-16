package br.com.rstephano.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.core.UriBuilder;

import br.com.rstephano.objects.Usuario;
import br.com.rstephano.objects.wrappers.Usuarios;
import br.com.rstephano.services.UsuarioService;

@Path("v1/usuario")
public class UsuarioResourceV1 {
	
	@Context
	private HttpHeaders headers;
	private UsuarioService service;

	public UsuarioResourceV1() {
		super();
		service = new UsuarioService();
	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response listar() {
		List<Usuario> usuarios = service.listar();
		return Response.status(Status.OK).entity(new Usuarios(usuarios)).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response incluir(@Valid Usuario usuario) throws Exception {
		service.inserir(usuario);
		URI location = UriBuilder.fromPath("v1/usuario/{id}").build(usuario.getId());
		return Response.status(Status.CREATED).entity(usuario).location(location).build();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response recuperar(@PathParam("id") String id) throws URISyntaxException {
		Usuario usuario = service.recuperar(id);
		if (usuario != null) {
			return Response.status(Status.OK).entity(usuario).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("").build();
		}
	}

}
