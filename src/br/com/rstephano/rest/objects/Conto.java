package br.com.rstephano.rest.objects;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Conto {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "autorId")
	@NotNull
	@NotEmpty
	private String autorId;
	@NotNull
	@NotEmpty
	@XmlElement(name = "titulo")
	private String titulo;
	@NotNull
	@NotEmpty
	@XmlElement(name = "conto")
	private String conto;

	public Conto(String id, String autorId, String titulo, String conto) {
		super();
		this.id = id;
		this.autorId = autorId;
		this.titulo = titulo;
		this.conto = conto;
	}

	public Conto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAutorId() {
		return autorId;
	}

	public void setAutorId(String autorId) {
		this.autorId = autorId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConto() {
		return conto;
	}

	public void setConto(String conto) {
		this.conto = conto;
	}
}
