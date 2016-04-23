package br.com.rstephano.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public enum MongoResource {
	INSTANCE;
	private MongoClient mongoClient;
	private Properties properties;
	private Datastore dataStore;

	private MongoResource() {
		try {
			if (properties == null) {
				try {
					properties = loadProperties();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (mongoClient == null)
				mongoClient = newClient();

			if (dataStore == null)
				dataStore = newDataStore();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Properties loadProperties() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = MongoResource.class.getResourceAsStream("/mongodb.properties");
		if (inputStream == null) {
			throw new FileNotFoundException("not loaded!");
		}
		properties.load(inputStream);
		return properties;
	}

	private MongoClient newClient() {
		return new MongoClient(properties.getProperty("host"), Integer.valueOf(properties.getProperty("port")));
	}

	private Datastore newDataStore() {
		Morphia morphia = new Morphia();
		morphia.mapPackage(properties.getProperty("entitiespackage"));
		return morphia.createDatastore(mongoClient, properties.getProperty("database"));
	}

	public Datastore getDataStore() {
		return dataStore;
	}

	// super();
	// MongoClient mongoClient = new MongoClient("localhost", 27017);
	// Morphia morphia = new Morphia();
	// morphia.mapPackage("br.com.rstephano.db.entities");
	// dataStore = morphia.createDatastore(mongoClient, "testesmongo");
	// }

}
