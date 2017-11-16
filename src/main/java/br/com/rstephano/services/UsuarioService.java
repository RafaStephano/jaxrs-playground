package br.com.rstephano.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import br.com.rstephano.db.repositories.UsuarioRepository;
import br.com.rstephano.Funcao;
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
					new DateTime(usuariosDb.get(i).getDataCadastro()),
					converteArrayFuncaoToFuncoes(usuariosDb.get(i).getFuncoes())
				)
			);
		}
		return usuarios;
	}

	public void inserir(@Valid Usuario usuario) throws Exception {
		ArrayList<String> listaFuncoes = new ArrayList<>();
		if (usuario.getFuncoes() == null || usuario.getFuncoes().size() == 0) {
			listaFuncoes.add(Funcao.USUARIO);
		} else {
			for (int i = 0; i < usuario.getFuncoes().size(); i++) {
				listaFuncoes.add(usuario.getFuncoes().get(i));
			}
		}
		br.com.rstephano.db.entities.Usuario usuarioDb = new br.com.rstephano.db.entities.Usuario(
			null, 
			usuario.getUsuario(), 
			usuario.getEmail(), 
			usuario.getSenha(), 
			usuario.getDataCadastro().toDate(),
			listaFuncoes.toArray(new String[listaFuncoes.size()])
		);
		repository.inserir(usuarioDb);
		usuario.setId(usuarioDb.getId().toHexString());
		usuario.setDataCadastro(new DateTime(usuarioDb.getDataCadastro()));
	}
	
	public Usuario recuperar(String id) {
		br.com.rstephano.db.entities.Usuario usuarioDb = repository.recuperar(new ObjectId(id));
		if (usuarioDb != null) {
			Usuario usuario = new Usuario(
				usuarioDb.getId().toHexString(),
				usuarioDb.getUsuario(),
				usuarioDb.getEmail(),
				usuarioDb.getSenha(),
				new DateTime(usuarioDb.getDataCadastro()),
				converteArrayFuncaoToFuncoes(usuarioDb.getFuncoes())
			);
			return usuario;
		} else {
			return null;
		}
	}

	private List<String> converteArrayFuncaoToFuncoes(String[] funcoes) {
		List<String> funcoesRetorno = new ArrayList<String>();
		if (funcoes != null) {
			for (int i = 0; i < funcoes.length; i++) {
				funcoesRetorno.add(funcoes[i]);
			}
		}
		return funcoesRetorno;
	}
}
