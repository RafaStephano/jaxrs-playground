package br.com.rstephano.db.repositories;

import org.bson.types.ObjectId;

import br.com.rstephano.db.MongoResource;
import br.com.rstephano.db.entities.Conto;

public class ContoRepository {

	public void inserir(Conto conto) {
		MongoResource.INSTANCE.getDataStore().save(conto);
	}

	public Conto recuperar(ObjectId objectId) {
		return MongoResource.INSTANCE.getDataStore().get(Conto.class, objectId);
	}

}
