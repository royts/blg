package com.royts.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.royts.PostDetails;
import com.sun.org.apache.bcel.internal.generic.NEW;

// A wrapper object to allow jaxB array list serialization

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ PostDetails.class })
public class PostsDetails {

	//@XmlElementWrapper(name = "postDetails")
	//@XmlElement(name = "postDetails", type = PostDetails.class)
	List<PostDetails> postsDetails = new ArrayList<PostDetails>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PostsDetails() {
	}

	public PostsDetails(List<PostDetails> postsDetails) {
		this.postsDetails = postsDetails;
	}

	public List<PostDetails> getPostsDetails() {
		return this.postsDetails;
	}

	public void setPostsDetails (List<PostDetails> postsDetails) {
		this.postsDetails = postsDetails;
	}

}
