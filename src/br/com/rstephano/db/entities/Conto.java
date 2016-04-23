package br.com.rstephano.db.entities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "contos", noClassnameStored = true)
public class Conto {
	@Id
	private ObjectId id;
	private String autorId;
	private String titulo;
	private String conto;
	private Date dataCadastro;

	public Conto(ObjectId id, String autorId, String titulo, String conto, Date dataCadastro) {
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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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
