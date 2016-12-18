package br.com.rstephano.db.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "autores", noClassnameStored = true)
public class Autor {
	@Id
	private ObjectId id;
	private String nome;

	public Autor(ObjectId id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Autor() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
