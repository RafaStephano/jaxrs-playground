package br.com.rstephano.rest.objects;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Contos {
	@JsonProperty("contos")
	public List<Conto> contos;

	public Contos() {
		super();
	}

	public Contos(List<Conto> contos) {
		super();
		this.contos = contos;
	}
}
