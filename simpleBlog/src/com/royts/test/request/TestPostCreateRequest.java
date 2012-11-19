package com.royts.test.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.royts.rest.BlgResponse;
import com.royts.rest.request.PostCreateRequest;

public class TestPostCreateRequest {

	final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());
	DatastoreService dataStore;

	PostCreateRequest request;
	MockedGAEStorage storageMock;

	@Before
	public void setUp() {
		this.storageMock = new MockedGAEStorage();
	}

	@Test
	public void testGetResponse_nullTitle_badRequest() {
		this.request = new PostCreateRequest(storageMock, null, "my content", "autors@mail");
		BlgResponse response= request.getResponse();
		assertEquals (response.getResponse().getStatus() , HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void testGetResponse_nullContent_badRequest() {
		this.request = new PostCreateRequest(storageMock, "my title", null, "autors@mail");
		BlgResponse response= request.getResponse();
		assertEquals (response.getResponse().getStatus() , HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void testGetResponse_nullAuthorMail_badRequest() {
		this.request = new PostCreateRequest(storageMock, "my title", "my content", null);
		BlgResponse response= request.getResponse();
		assertEquals (response.getResponse().getStatus() , HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void testGetResponse_simplePost_created() {
		String postTitle = "my title";
		String postContent = "my content";
		String authorsMail = "authors@mail";
		this.request = new PostCreateRequest(storageMock, postTitle, postContent, authorsMail);
		
		BlgResponse response= request.getResponse();
		
		assertEquals (response.getResponse().getStatus() , HttpStatus.SC_OK);
		assertTrue (storageMock.getSavedPosts().size() == 1);
		assertEquals(storageMock.getSavedPosts().get(0).getTitle(), postTitle);
		assertEquals(storageMock.getSavedPosts().get(0).getContent(), postContent);
		assertEquals(storageMock.getSavedPosts().get(0).getAuthorsMail(), authorsMail);
	}

}
