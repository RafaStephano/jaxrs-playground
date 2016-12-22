package br.com.rstephano.objects;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.rstephano.constraints.EachFuncao;
import br.com.rstephano.constraints.Senha;
import br.com.rstephano.objects.adapters.JsonDateTimeDeserializer;
import br.com.rstephano.objects.adapters.JsonDateTimeSerializer;
import br.com.rstephano.objects.adapters.XmlDateTimeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
	private String id;
	@NotNull
	@NotEmpty
	private String usuario;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	@Senha
	private String senha;
	@NotNull
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	@XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
	private DateTime dataCadastro;
	@XmlElementWrapper(name = "funcoes")
	@XmlElement(name = "funcao")
	@EachFuncao
	private List<String> funcoes;

	public Usuario(String id, String usuario, String email, String senha, DateTime dataCadastro, List<String> funcoes) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.funcoes = funcoes;
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

	public List<String> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<String> funcoes) {
		this.funcoes = funcoes;
	}
}
