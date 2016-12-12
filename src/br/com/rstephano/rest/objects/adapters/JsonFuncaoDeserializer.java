package br.com.rstephano.rest.objects.adapters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.rstephano.objects.Funcao;

public class JsonFuncaoDeserializer extends JsonDeserializer<Funcao> {

	@Override
	public Funcao deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		for (Funcao f : Funcao.values()) {
			if (f.getFuncao().trim().equals(jsonparser.getText())) {
				return f;
			}
		}
		return null;
	}

}
