package com.royts.test;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
import com.royts.PostDetails;
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
	public void testSavePost_newPost_saved() throws StorageException, postNotFoundException {
		Post newPost = new Post(new Long(-1), "My title", "myContent",
				"Author mail");
		PreparedQuery preparedQuery = this.dataStore.prepare(new Query("Post"));

		storage.savePost(newPost);

		assertEquals(1, preparedQuery.countEntities(withLimit(10)));
		Entity entity = preparedQuery.asSingleEntity();
		assertEquals((String) entity.getProperty("PostTitle"), newPost.getTitle());
		assertEquals((String) entity.getProperty("PostContent"), newPost.getContent());
		assertEquals((String) entity.getProperty("PostAuthorMail"), newPost.getAuthorsMail());
	}

	// @Test
	// public void testSavePost_updateExisting_updated() throws
	// StorageException, postNotFoundException {
	// Post newPost = new Post(new Long(-1), "My title", "myContent",
	// "Author mail");
	// PreparedQuery preparedQuery = this.dataStore.prepare(new Query("Post"));
	//
	// Post original = storage.savePost(newPost);
	//
	// Post changed = new Post(original.getId(), "changed title",
	// "changed content", "changed authors mail");
	//
	// this.storage.savePost(changed);
	//
	// assertEquals(1, preparedQuery.countEntities(withLimit(10)));
	// Entity entity = preparedQuery.asSingleEntity();
	// assertEquals((String) entity.getProperty("PostTitle"),
	// changed.getTitle());
	// assertEquals((String) entity.getProperty("PostContent"),
	// changed.getContent());
	// assertEquals((String) entity.getProperty("PostAuthorMail"),
	// changed.getAuthorsMail());
	// }

	public void testGetAllPostsDetails_createSome_getThem() throws StorageException, postNotFoundException {
		Post post1 = new Post(
				new Long(-1),
				"title 1",
				"content 1",
				"Author mail 1");
		Post post2 = new Post(
				new Long(-1),
				"title 2",
				"content 2",
				"Author mail 2");
		Post post3 = new Post(
				new Long(-1),
				"title 3",
				"content 3",
				"Author mail 3");
		this.storage.savePost(post1);
		this.storage.savePost(post2);
		this.storage.savePost(post3);

		List<PostDetails> posts = this.storage.getAllPostsDetails();

		assertTrue(posts.size() == 3);

		assertEquals(posts.get(0).getPostTitle(), post1.getTitle());
		assertEquals(posts.get(1).getPostTitle(), post2.getTitle());
		assertEquals(posts.get(2).getPostTitle(), post3.getTitle());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPostById_postIdNull_throw() throws StorageException {
		storage.getPostById(null);
	}

	@Test
	public void testGetPostById_notExists_returnNull() throws StorageException {
		assertEquals(storage.getPostById(new Long(101010)), null);
	}

	@Test
	public void testGetPostById_twoExists_returnTheRightOne() throws StorageException {
		Post post1 = new Post(new Long(-1), "title1", "content1", "author1");
		Post post2 = new Post(new Long(-1), "title2", "content2", "author2");

		Post post1Saved = storage.savePost(post1);
		Post post2Saved = storage.savePost(post2);

		Post post1Loaded = storage.getPostById(post1Saved.getId());
		assertEquals(post1Loaded.getTitle(), post1.getTitle());
		assertEquals(post1Loaded.getContent(), post1.getContent());
		assertEquals(post1Loaded.getAuthorsMail(), post1.getAuthorsMail());

		Post post2Loaded = storage.getPostById(post2Saved.getId());
		assertEquals(post2Loaded.getTitle(), post2.getTitle());
		assertEquals(post2Loaded.getContent(), post2.getContent());
		assertEquals(post2Loaded.getAuthorsMail(), post2.getAuthorsMail());
	}

}
