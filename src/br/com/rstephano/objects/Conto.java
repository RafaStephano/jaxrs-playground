package br.com.rstephano.objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.rstephano.rest.objects.adapters.JsonDateTimeDeserializer;
import br.com.rstephano.rest.objects.adapters.JsonDateTimeSerializer;
import br.com.rstephano.rest.objects.adapters.XmlDateTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Conto {
	private String id;
	@NotNull
	@NotEmpty
	private String autorId;
	@NotNull
	@NotEmpty
	private String titulo;
	@NotNull
	@NotEmpty
	@Size(min = 100)
	private String conto;
	@NotNull
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
	private DateTime dataCadastro;

	public Conto() {
		super();
	}

	public Conto(String id, String autorId, String titulo, String conto, DateTime dataCadastro) {
		super();
		this.id = id;
		this.autorId = autorId;
		this.titulo = titulo;
		this.conto = conto;
		this.dataCadastro = dataCadastro;
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

	public DateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
