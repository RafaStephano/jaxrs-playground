package br.com.rstephano.db.entities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "publicacoes", noClassnameStored = true)
public class Publicacao {
	@Id
	private ObjectId id;
	private String usuarioId;
	private String titulo;
	private String publicacao;
	private Date dataCadastro;

	public Publicacao(ObjectId id, String usuarioId, String titulo, String publicacao, Date dataCadastro) {
		super();
		this.id = id;
		this.usuarioId = usuarioId;
		this.titulo = titulo;
		this.publicacao = publicacao;
		this.dataCadastro = dataCadastro;
	}

	public Publicacao() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(String publicacao) {
		this.publicacao = publicacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
