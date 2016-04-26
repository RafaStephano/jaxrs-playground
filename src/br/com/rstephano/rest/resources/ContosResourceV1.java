package br.com.rstephano.rest.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.bson.types.ObjectId;

import br.com.rstephano.db.repositories.ContoRepository;
import br.com.rstephano.rest.objects.Conto;
import br.com.rstephano.rest.objects.ValidationErrorDetail;
import br.com.rstephano.rest.objects.ValidationErrorHeader;

@Path("v1/conto")
public class ContosResourceV1 {

	private ContoRepository contoRepository;

	public ContosResourceV1() {
		super();
		contoRepository = new ContoRepository();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response incluir(Conto conto) throws URISyntaxException {
		validarCampos(conto);
		br.com.rstephano.db.entities.Conto contoDb = new br.com.rstephano.db.entities.Conto(null, conto.getAutorId(), conto.getTitulo(), conto.getConto(), conto.getDataCadastro());
		contoRepository.inserir(contoDb);
		conto.setId(contoDb.getId().toHexString());
		conto.setDataCadastro(contoDb.getDataCadastro());
		URI location = UriBuilder.fromPath("conto/{id}").build(contoDb.getId().toString());
		return Response.status(Status.CREATED).entity(conto).location(location).build();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response recuperar(@PathParam("id") String id) throws URISyntaxException {
		br.com.rstephano.db.entities.Conto contoDb = contoRepository.recuperar(new ObjectId(id));
		if (contoDb != null) {
			Conto conto = new Conto();
			conto.setId(contoDb.getId().toHexString());
			conto.setAutorId(contoDb.getAutorId());
			conto.setTitulo(contoDb.getTitulo());
			conto.setConto(contoDb.getConto());
			conto.setDataCadastro(contoDb.getDataCadastro());
			return Response.status(Status.OK).entity(conto).build();
		} else {
			return Response.status(Status.NOT_FOUND).entity("").build();
		}
	}

	@SuppressWarnings("unchecked")
	private void validarCampos(Conto conto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Conto>> constraintViolations = validator.validate(conto);
		if (constraintViolations.size() > 0) {
			ConstraintViolation<Conto>[] array = constraintViolations.toArray(new ConstraintViolation[constraintViolations.size()]);
			ValidationErrorHeader validationErrorHeader = new ValidationErrorHeader();
			validationErrorHeader.setClassName(Conto.class.getSimpleName());
			List<ValidationErrorDetail> errorsDetails = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				ConstraintViolation<Conto> constraintViolation = array[i];
				String constraintFullName = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType().toString();
				String constraintName = constraintFullName.substring(constraintFullName.lastIndexOf(".") + 1);
				errorsDetails.add(new ValidationErrorDetail(constraintViolation.getPropertyPath().toString(), constraintName));
			}
			validationErrorHeader.setErrors(errorsDetails);
			Response response = Response.status(Status.BAD_REQUEST).entity(validationErrorHeader).build();
			throw new WebApplicationException(response);
		}
	}

}
