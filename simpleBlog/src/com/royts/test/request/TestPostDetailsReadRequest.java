package com.royts.test.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.royts.Post;
import com.royts.rest.BlgResponse;
import com.royts.rest.PostsDetails;
import com.royts.rest.request.PostDetailsReadRequest;

public class TestPostDetailsReadRequest {


	final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	DatastoreService dataStore;

	PostDetailsReadRequest request;
	MockedGAEStorage storageMock;
	
	@Before
	public void setUp() {
		helper.setUp();
		this.dataStore = DatastoreServiceFactory.getDatastoreService();
		this.storageMock = new MockedGAEStorage();
		this.request = new PostDetailsReadRequest(storageMock);
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	@Test
	public void testGetResponse_noPosts_emptyResponse () {
		BlgResponse response = request.getResponse();
		PostsDetails postsDetails = (PostsDetails)response.getData();
		assertTrue(postsDetails.getPostsDetails().size() == 0);
	}
	
	@Test
	public void testGetResponse_twoPosts_responseContainsPosts () {
		storageMock.addPost(new Post(new Long(1010), "title 1", "content 1", "mai1@1"));
		storageMock.addPost(new Post(new Long(1011), "title 2", "content 2", "mai1@2"));
		
		BlgResponse response = request.getResponse();
		assertEquals (response.getResponse().getStatus() , HttpStatus.SC_OK);
		PostsDetails postsDetails = (PostsDetails) response.getData();
		assertTrue(postsDetails.getPostsDetails().size() == 2);
	}
}
