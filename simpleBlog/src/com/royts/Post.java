package com.royts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {

	String title;
	String content;
	String authorsMail;
	long id;

	public Post () {} //for jaxB
	
	public Post(Long id, String title, String content, String authorsMail) {
		
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
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorsMail = authorsMail;
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

	public long getId() {
		return this.id;
	}

}
