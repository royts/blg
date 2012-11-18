package com.royts.storage;

import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class StorageFactory {

	public static Storage get() {
		return new GAEDataStore(DatastoreServiceFactory.getDatastoreService());
	}
}
