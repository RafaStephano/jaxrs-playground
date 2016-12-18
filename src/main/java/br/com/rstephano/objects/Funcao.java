package br.com.rstephano.objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.rstephano.rest.objects.adapters.JsonFuncaoDeserializer;
import br.com.rstephano.rest.objects.adapters.JsonFuncaoSerializer;
import br.com.rstephano.rest.objects.adapters.XmlFuncaoAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonSerialize(using = JsonFuncaoSerializer.class)
@JsonDeserialize(using = JsonFuncaoDeserializer.class)
@XmlJavaTypeAdapter(XmlFuncaoAdapter.class)
public enum Funcao {
	ADMIN("administrador"), USUARIO("usuario");
	private String funcao;

	private Funcao(String funcao) {
		this.funcao = funcao;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
}
