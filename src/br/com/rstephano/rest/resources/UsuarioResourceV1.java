package br.com.rstephano.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import javax.ws.rs.core.UriBuilder;

import br.com.rstephano.MessageBundleUtil;
import br.com.rstephano.bundles.LocaleSpecificMessageInterpolator;
import br.com.rstephano.objects.Conto;
import br.com.rstephano.objects.Usuario;
import br.com.rstephano.objects.ValidationErrorDetail;
import br.com.rstephano.objects.ValidationErrorHeader;
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
	public Response incluir(Usuario usuario) throws Exception {
		validarUsuario(usuario);
		service.inserir(usuario);
		URI location = UriBuilder.fromPath("usuario/{id}").build(usuario.getId());
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

	@SuppressWarnings("unchecked")
	private void validarUsuario(Usuario usuario) {
		if (usuario == null) {
			Response response = Response.status(Status.BAD_REQUEST).entity(MessageBundleUtil.getMessage(MessageBundleUtil.Key.VALIDATIONS, headers, "objeto_requerido", new String[]{Usuario.class.getSimpleName()})).build();
			throw new WebApplicationException(response);
		} else {
			Configuration<?> config = Validation.byDefaultProvider().configure();
			config.messageInterpolator(new LocaleSpecificMessageInterpolator(Validation.byDefaultProvider().configure().getDefaultMessageInterpolator()));
			ValidatorFactory factory = config.buildValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
			if (constraintViolations.size() > 0) {
				ConstraintViolation<Conto>[] array = constraintViolations.toArray(new ConstraintViolation[constraintViolations.size()]);
				ValidationErrorHeader validationErrorHeader = new ValidationErrorHeader();
				validationErrorHeader.setClassName(Conto.class.getSimpleName());
				List<ValidationErrorDetail> errorsDetails = new ArrayList<>();
				for (int i = 0; i < array.length; i++) {
					ConstraintViolation<Conto> constraintViolation = array[i];
					String constraintFullName = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().toString();
					String constraintName = constraintFullName.substring(constraintFullName.lastIndexOf(".") + 1);
					errorsDetails.add(new ValidationErrorDetail(constraintViolation.getPropertyPath().toString(), constraintName, constraintViolation.getMessage()));
				}
				validationErrorHeader.setErrors(errorsDetails);
				Response response = Response.status(Status.BAD_REQUEST).entity(validationErrorHeader).build();
				throw new WebApplicationException(response);
			}
		}
	}

}
