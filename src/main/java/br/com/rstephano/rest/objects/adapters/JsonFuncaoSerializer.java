package br.com.rstephano.rest.objects.adapters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.rstephano.objects.Funcao;

public class JsonFuncaoSerializer extends JsonSerializer<Funcao> {

	@Override
	public void serialize(Funcao value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		gen.writeString(value.getFuncao());
	}

}
