package com.royts.test;

import static org.junit.Assert.*;

import java.util.Date;


import org.junit.Test;

import com.royts.Post;

public class TestPost {
	@Test
	public void testCtor_sinpleParameters_gettersReturnsCorrectly() {
		Long id = new Long(102030);
		String title = "my title";
		String content = "my content";
		String authorsMail = "authors@mail.com";

		Post post = new Post(id, title, content, authorsMail,new Date());

		assertTrue(id.equals(post.getId()));
		assertEquals(title, post.getTitle());
		assertEquals(content, post.getContent());
		assertEquals(authorsMail, post.getAuthorsMail());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCtor_nullId_throw() {
		new Post(null, "thf", "abc", "efg", new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCtor_nullTitle_throw() {
		new Post(new Long(102030), null, "abc", "efg", new Date());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCtor_nullContent_throw() {
		new Post(new Long(102030), "abc", null, "efg", new Date());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCtor_nullDate_throw() {
		new Post(new Long(102030), "abc", null, "efg", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCtor_nullMail_throw() {
		new Post(new Long(102030), "abc", "efg", null, new Date());
	}

}
