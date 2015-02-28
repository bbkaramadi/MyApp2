package controllers;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MorphiaObject {

	final static Datastore datastore = createDS();

	private static Datastore createDS() {

		Datastore ds=null;
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			Morphia morphia = new Morphia();
			ds=morphia.createDatastore(mongo, "employees");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	public static Datastore getDataStore() {

		return datastore;

	}

}