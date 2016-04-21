package br.com.rstephano.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class DB {

	private Datastore dataStore;

	public DB() {
		super();
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.rstephano.db.entities");
		dataStore = morphia.createDatastore(mongoClient, "testesmongo");
	}

	public Datastore getDataStore() {
		return dataStore;
	}

}
