package br.com.rstephano.db.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

import br.com.rstephano.db.MongoResource;
import br.com.rstephano.db.entities.Conto;

public class ContoRepository {

	public List<Conto> listar() {
		Query<Conto> query = MongoResource.INSTANCE.getDataStore().createQuery(Conto.class).order("-dataCadastro");
		return query.asList();
	}

	public void inserir(Conto conto) {
		MongoResource.INSTANCE.getDataStore().save(conto);
	}

	public Conto recuperar(ObjectId objectId) {
		return MongoResource.INSTANCE.getDataStore().get(Conto.class, objectId);
	}

}
