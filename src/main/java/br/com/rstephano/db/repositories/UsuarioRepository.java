package br.com.rstephano.db.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import br.com.rstephano.db.MongoResource;
import br.com.rstephano.db.entities.Usuario;

public class UsuarioRepository {

	public List<Usuario> listar() {
		Query<Usuario> query = MongoResource.INSTANCE.getDataStore().createQuery(Usuario.class).order("-dataCadastro");
		return query.asList();
	}

	public void inserir(Usuario usuario) {
		MongoResource.INSTANCE.getDataStore().save(usuario);
	}

	public Usuario recuperar(ObjectId objectId) {
		return MongoResource.INSTANCE.getDataStore().get(Usuario.class, objectId);
	}

}
