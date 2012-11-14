package com.royts.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.royts.Post;

public class TestPost {
	@Test
	public void testCtor_sinpleParameters_gettersReturnsCorrectly () {
		String title = "my title";
		String content = "my content";
		String authorsMail = "authors@mail.com";
		
		Post post = new Post (title, content, authorsMail);
		
		assertEquals(title, post.getTitle());
		assertEquals(content, post.getContent());
		assertEquals(authorsMail, post.getAuthorsMail());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCtor_nullTitle_throw () {
		new Post(null, "abc", "efg");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testCtor_nullContent_throw () {
		new Post("abc", null , "efg");
	}
	@Test (expected = IllegalArgumentException.class)
	public void testCtor_nullMail_throw () {
		new Post("abc", "efg", null);
	}

}
