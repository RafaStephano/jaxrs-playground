package br.com.rstephano.rest;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

	final ObjectMapper mapper = new ObjectMapper();

	public ObjectMapperContextResolver() {
		//mapper.registerModule(new JodaModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}

}
