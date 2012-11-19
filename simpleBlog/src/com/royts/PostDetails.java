package com.royts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostDetails {
	String postTitle;
	Long postId;
	
	public PostDetails () {} // for jaxB
	public PostDetails(Long postId, String postTitle) {
		this.postTitle = postTitle;
		this.postId = postId;
	}
	
	@XmlElement(name ="id")
	public Long getPostId() {
		return postId;
	}
	
	@XmlElement(name ="title")
	public String getPostTitle() {
		return postTitle;
	}
	
}
