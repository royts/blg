package com.royts.test;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
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
				"Author mail", new Date());
		PreparedQuery preparedQuery = this.dataStore.prepare(new Query("Post"));

		storage.savePost(newPost);

		assertEquals(1, preparedQuery.countEntities(withLimit(10)));
		Entity entity = preparedQuery.asSingleEntity();
		assertEquals((String) entity.getProperty("PostTitle"), newPost.getTitle());
		assertEquals((String) entity.getProperty("PostContent"), newPost.getContent());
		assertEquals((String) entity.getProperty("PostAuthorMail"), newPost.getAuthorsMail());
		assertEquals((Date) entity.getProperty("postCreateDate"), newPost.getCreateDate());
	}

	@Test
	public void testGetPostsDetails_createMultipleWithDifferentCreateDate_getThemOrderedByCreateDate() throws StorageException, postNotFoundException {

		long DAY_IN_MS = 1000 * 60 * 60 * 24;

		Post postEarlier = new Post(
				new Long(-1),
				"title 1",
				"content 1",
				"Author mail 1",
				new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)));

		Post postMiddle = new Post(
				new Long(-1),
				"title 2",
				"content 2",
				"Author mail 2",
				new Date(System.currentTimeMillis() - (3 * DAY_IN_MS)));
		Post postLatest = new Post(
				new Long(-1),
				"title 3",
				"content 3",
				"Author mail 3",
				new Date(System.currentTimeMillis()));

		this.storage.savePost(postLatest);
		this.storage.savePost(postEarlier);
		this.storage.savePost(postMiddle);

		List<PostDetails> posts = this.storage.getPostsDetails(10);

		assertTrue(posts.size() == 3);

		assertEquals(posts.get(0).getPostTitle(), postLatest.getTitle());
		assertEquals(posts.get(1).getPostTitle(), postMiddle.getTitle());
		assertEquals(posts.get(2).getPostTitle(), postEarlier.getTitle());
	}

	@Test
	public void testGetPostsDetails_create15Posts_getPostsByNumber() throws StorageException, postNotFoundException {

		Post post;

		for (int i = 0; i < 15; i++) {
			post = new Post(new Long(-1), "title 1", "content 1", "Author mail 1", new Date());
			this.storage.savePost(post);
		}

		List<PostDetails> posts = this.storage.getPostsDetails(10);
		assertTrue(posts.size() == 10);

		posts = this.storage.getPostsDetails(14);
		assertTrue(posts.size() == 14);
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
		Post post1 = new Post(new Long(-1), "title1", "content1", "author1", new Date());
		Post post2 = new Post(new Long(-1), "title2", "content2", "author2", new Date());

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
