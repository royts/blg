package com.royts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.royts.Post;

public class TestPost {
	
	@Test
	public void testCtor_simpleDetails_gettersReturnsCorrectly () {
		String title = "my title";
		String content = "my content";
		String authorsMail = "authors@mail.com";
		
		Post post = new Post (title, content, authorsMail);
		
		assertEquals(title, post.getTitle());
		assertEquals(content, post.getContent());
		assertEquals(authorsMail, post.getAuthorsMail());
	}

}
