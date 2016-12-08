package br.com.rstephano.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import br.com.rstephano.db.repositories.UsuarioRepository;
import br.com.rstephano.objects.Conto;
import br.com.rstephano.objects.Usuario;

public class UsuarioService {
	
	private UsuarioRepository repository;
	
	public UsuarioService() {
		super();
		repository = new UsuarioRepository();
	}

	public List<Usuario> listar() {
		List<br.com.rstephano.db.entities.Usuario> usuariosDb = repository.listar();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (int i = 0; i < usuariosDb.size(); i++) {
			usuarios.add(
				new Usuario(
					usuariosDb.get(i).getId().toHexString(),
					usuariosDb.get(i).getUsuario(),
					usuariosDb.get(i).getEmail(),
					usuariosDb.get(i).getSenha(),
					new DateTime(usuariosDb.get(i).getDataCadastro())
				)
			);
		}
		return usuarios;
	}

	public void inserir(Usuario usuario) {
		br.com.rstephano.db.entities.Usuario usuarioDb = new br.com.rstephano.db.entities.Usuario(
			null, 
			usuario.getUsuario(), 
			usuario.getEmail(), 
			usuario.getSenha(), 
			usuario.getDataCadastro().toDate()
		);
		repository.inserir(usuarioDb);
		usuario.setId(usuarioDb.getId().toHexString());
		usuario.setDataCadastro(new DateTime(usuarioDb.getDataCadastro()));
	}
	
	public Usuario recuperar(String id) {
		br.com.rstephano.db.entities.Usuario usuarioDb = repository.recuperar(new ObjectId(id));
		if (usuarioDb != null) {
			Usuario usuario = new Usuario();
			usuario.setId(usuarioDb.getId().toHexString());
			usuario.setUsuario(usuarioDb.getUsuario());
			usuario.setEmail(usuarioDb.getEmail());
			usuario.setSenha(usuarioDb.getSenha());
			usuario.setDataCadastro(new DateTime(usuarioDb.getDataCadastro()));
			return usuario;
		} else {
			return null;
		}
	}
}
