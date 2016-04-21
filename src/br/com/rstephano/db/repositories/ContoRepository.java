package br.com.rstephano.db.repositories;

import org.mongodb.morphia.Key;

import br.com.rstephano.db.DB;
import br.com.rstephano.db.entities.Conto;

public class ContoRepository {

	private DB db;

	public ContoRepository() {
		super();
		db = new DB();
	}

	public Key<Conto> inserir(Conto conto) {
		return db.getDataStore().save(conto);
	}

}
