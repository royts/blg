package com.royts.test.request;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.apache.http.HttpStatus;


import com.royts.Post;
import com.royts.rest.request.PostReadRequest;
import static org.junit.Assert.*;

public class TestPostReadRequest {

	private MockedGAEStorage storageMock;

	@Before
	public void setUp() {
		this.storageMock = new MockedGAEStorage();
	}
	
	@Test
	public void testGetResponse_nullPostId_badRequest () {
		
		PostReadRequest request = new PostReadRequest (storageMock, null);
		
		assertEquals(request.getResponse().getResponse().getStatus(), HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void testGetResponse_postNotExists_notFound () {
		PostReadRequest request = new PostReadRequest (storageMock, "102030");
		assertEquals(request.getResponse().getResponse().getStatus(), HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void testGetResponse_postIdNotANumber_badRequest () {
		PostReadRequest request = new PostReadRequest (storageMock, "abc");
		assertEquals(request.getResponse().getResponse().getStatus(), HttpStatus.SC_BAD_REQUEST);
	}
	
	@Test
	public void testGetResponse_postExists_responseWithPost () {
		String postId = "102030";
		Post post = new Post (new Long(postId),  "my title", "my content", "authors@mail" ,new Date());
		storageMock.addPost(post);
		
		PostReadRequest request = new PostReadRequest (storageMock, postId);
		
		assertEquals(request.getResponse().getResponse().getStatus(), HttpStatus.SC_OK);
		assertEquals(((Post)request.getResponse().getData())
				.getId(), post.getId());
		assertEquals(((Post)request.getResponse().getData())
				.getTitle(), post.getTitle());
		assertEquals(((Post)request.getResponse().getData())
				.getContent(), post.getContent());
		assertEquals(((Post)request.getResponse().getData())
				.getAuthorsMail(), post.getAuthorsMail());
		assertEquals(((Post)request.getResponse().getData())
				.getCreateDate(), post.getCreateDate());
	}
}
