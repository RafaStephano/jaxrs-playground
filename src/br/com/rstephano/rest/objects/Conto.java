package br.com.rstephano.rest.objects;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.rstephano.rest.objects.adapters.XmlDateAdapter;

@XmlRootElement
public class Conto {
	@XmlElement(name = "id")
	private String id;
	@XmlElement(name = "autorId")
	@NotNull
	@NotEmpty
	private String autorId;
	@XmlElement(name = "titulo")
	@NotNull
	@NotEmpty
	private String titulo;
	@XmlElement(name = "conto")
	@NotNull
	@NotEmpty
	private String conto;
	@XmlElement(name = "dataCadastro")
	@NotNull
	@XmlJavaTypeAdapter(XmlDateAdapter.class)
	private Date dataCadastro;

	public Conto(String id, String autorId, String titulo, String conto, Date dataCadastro) {
		super();
		this.id = id;
		this.autorId = autorId;
		this.titulo = titulo;
		this.conto = conto;
		this.dataCadastro = dataCadastro;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
