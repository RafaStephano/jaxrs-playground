package br.com.rstephano.objects.wrappers;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.rstephano.objects.Usuario;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {
	@JsonProperty("usuarios")
	@XmlElement(name = "usuario")
	public List<Usuario> usuarios;

	public Usuarios() {
		super();
	}

	public Usuarios(List<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
	}
}
