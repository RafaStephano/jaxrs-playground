package br.com.rstephano.objects;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
public class Usuario {
	private String id;
	@NotNull
	@NotEmpty
	private String usuario;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String senha;
	@NotNull
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
	private DateTime dataCadastro;

	public Usuario(String id, String usuario, String email, String senha, DateTime dataCadastro) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	public Usuario() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public DateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
