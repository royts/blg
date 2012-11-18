package com.royts.test;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.royts.Post;
import com.royts.storage.GAEDataStore;
import com.royts.storage.StorageException;
import com.royts.storage.postNotFoundException;

public class TestGAEDataStore {

	final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	DatastoreService dataStore;

	GAEDataStore storage;

	@Before
	public void setUp() {
		helper.setUp();
		this.dataStore = DatastoreServiceFactory.getDatastoreService();
		this.storage = new GAEDataStore(dataStore);
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testSavePost_newPost_saved() throws StorageException,
			postNotFoundException {
		Post newPost = new Post(new Long(-1), "My title", "myContent",
				"Author mail");
		PreparedQuery preparedQuery = this.dataStore.prepare(new Query("Post"));

		storage.savePost(newPost);

		assertEquals(1, preparedQuery.countEntities(withLimit(10)));
		Entity entity = preparedQuery.asSingleEntity();
		assertEquals((String) entity.getProperty("PostTitle"),
				newPost.getTitle());
		assertEquals((String) entity.getProperty("PostContent"),
				newPost.getContent());
		assertEquals((String) entity.getProperty("PostAuthorMail"),
				newPost.getAuthorsMail());
	}

	@Test
	public void testSavePost_updateExisting_updated() throws StorageException,
			postNotFoundException {
		Post newPost = new Post(new Long(-1), "My title", "myContent",
				"Author mail");
		PreparedQuery preparedQuery = this.dataStore.prepare(new Query("Post"));

		Post original = storage.savePost(newPost);

		Post changed = new Post(original.getId(), "changed title",
				"changed content", "changed authors mail");

		this.storage.savePost(changed);

		assertEquals(1, preparedQuery.countEntities(withLimit(10)));
		Entity entity = preparedQuery.asSingleEntity();
		assertEquals((String) entity.getProperty("PostTitle"),
				changed.getTitle());
		assertEquals((String) entity.getProperty("PostContent"),
				changed.getContent());
		assertEquals((String) entity.getProperty("PostAuthorMail"),
				changed.getAuthorsMail());
	}

}
