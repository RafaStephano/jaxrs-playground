package br.com.rstephano.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import br.com.rstephano.db.repositories.UsuarioRepository;
import br.com.rstephano.objects.Funcao;
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

	public void inserir(Usuario usuario) throws Exception {
		ArrayList<br.com.rstephano.db.entities.Funcao> listaFuncoes = new ArrayList<>();
		if (usuario.getFuncoes() == null || usuario.getFuncoes().length == 0) {
			listaFuncoes.add(br.com.rstephano.db.entities.Funcao.USUARIO);
		} else {
			// TODO Verificar se o usuário que envio a request está logado e se tem permissão para cadastrar o usuário informando a funcao
			for (int i = 0; i < usuario.getFuncoes().length; i++) {
				for (br.com.rstephano.db.entities.Funcao f : br.com.rstephano.db.entities.Funcao.values()) {
					if (f.getFuncao().trim().equals(usuario.getFuncoes()[i].getFuncao())) {
						listaFuncoes.add(f);
					}
				}
			}
			if (listaFuncoes.size() == 0) {
				throw new Exception("Favor informar uma função válida");
			}
		}
		br.com.rstephano.db.entities.Usuario usuarioDb = new br.com.rstephano.db.entities.Usuario(
			null, 
			usuario.getUsuario(), 
			usuario.getEmail(), 
			usuario.getSenha(), 
			usuario.getDataCadastro().toDate(),
			listaFuncoes.toArray(new br.com.rstephano.db.entities.Funcao[listaFuncoes.size()])
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

	private Funcao[] converteArrayFuncaoToFuncoes(br.com.rstephano.db.entities.Funcao[] funcoes) {
		List<Funcao> funcoesRetorno = new ArrayList<Funcao>();
		if (funcoes != null) {
			for (int i = 0; i < funcoes.length; i++) {
				for (Funcao f : Funcao.values()) {
					if (f.getFuncao().trim().equals(funcoes[i].getFuncao())) {
						funcoesRetorno.add(f);
					}
				}
			}
		}
		return funcoesRetorno.toArray(new Funcao[funcoesRetorno.size()]);
	}
}
