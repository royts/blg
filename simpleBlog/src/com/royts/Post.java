package com.royts;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {

	String title;
	String content;
	String authorsMail;
	Long id;
	private Date createDate;

	public Post () {} //for jaxB
	
	public Post(Long id, String title, String content, String authorsMail, Date createDate) {
		
		if(id == null) {
			throw new IllegalArgumentException ("posts ID null is illegal");
		}
		if(title == null) {
			throw new IllegalArgumentException ("posts title null is illegal");
		}
		if(content == null) {
			throw new IllegalArgumentException ("content null is illegal");
		}
		if(authorsMail == null) {
			throw new IllegalArgumentException ("authors mail null is illegal");
		}
		if(createDate == null) {
			throw new IllegalArgumentException ("create date null is illegal");
		}
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorsMail = authorsMail;
		this.createDate = createDate;
	}

	public Post(Long postId, Post copyFromPost) {
		this.id = postId;
		this.title = copyFromPost.getTitle();
		this.content = copyFromPost.getContent();
		this.authorsMail = copyFromPost.getAuthorsMail();
	}

	@XmlElement(name ="title")
	
	public String getTitle() {
		return title;
	}

	@XmlElement(name ="content")
	public String getContent() {
		return content;
	}

	@XmlElement(name ="authormail")
	public String getAuthorsMail() {
		return authorsMail;
	}
	
	@XmlElement(name ="createDate")
	public Date getCreateDate() {
		return this.createDate;
	}

	@XmlElement(name ="id")
	public Long getId() {
		return this.id;
	}

}
