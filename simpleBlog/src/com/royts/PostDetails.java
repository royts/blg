package com.royts;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostDetails {
	String postTitle;
	Long postId;
	Date createDate;
	
	public PostDetails () {} // for jaxB
	public PostDetails(Long postId, String postTitle, Date createDate) {
		this.postTitle = postTitle;
		this.postId = postId;
		this.createDate = createDate;
	}
	
	@XmlElement(name ="id")
	public Long getPostId() {
		return postId;
	}
	
	@XmlElement(name ="title")
	public String getPostTitle() {
		return postTitle;
	}
	
	@XmlElement(name ="createDate")
	public Date getCreateDate() {
		return createDate;
	}
	
}
