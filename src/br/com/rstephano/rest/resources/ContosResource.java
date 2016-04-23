package br.com.rstephano.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.bson.types.ObjectId;

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
		validar(conto);
		br.com.rstephano.db.entities.Conto contoDb = new br.com.rstephano.db.entities.Conto(null, conto.getAutorId(), conto.getTitulo(), conto.getConto(), conto.getDataCadastro());
		contoRepository.inserir(contoDb);
		conto.setId(contoDb.getId().toString());
		conto.setDataCadastro(contoDb.getDataCadastro());
		URI location = UriBuilder.fromPath("conto/{id}").build(contoDb.getId().toString());
		return Response.status(Status.CREATED).entity(conto).location(location).build();
	}

	@SuppressWarnings("unchecked")
	private void validar(Conto conto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Conto>> constraintViolations = validator.validate(conto);
		if (constraintViolations.size() > 0) {
			StringBuffer retorno = new StringBuffer();
			ConstraintViolation<Conto>[] array = constraintViolations.toArray(new ConstraintViolation[constraintViolations.size()]);
			for (int i = 0; i < array.length; i++) {
				ConstraintViolation<Conto> constraintViolation = array[i];
				String constraintFullName = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().toString();
				String constraintName = constraintFullName.substring(constraintFullName.lastIndexOf(".") + 1);
				retorno.append((i > 0 ? "|": "") + constraintViolation.getPropertyPath().toString() + "=" + constraintName);
			}
			Response response = Response.status(Status.BAD_REQUEST).entity(retorno.toString()).type(MediaType.TEXT_PLAIN).build();
			throw new WebApplicationException(response);
		}
	}

}
