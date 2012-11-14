package com.royts;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {

	String title;
	String content;
	String authorsMail;

	public Post () {} //for jaxB
	public Post(String title, String content, String authorsMail) {
		
		if(title == null) {
			throw new IllegalArgumentException ("posts title null is illegal");
		}
		if(content == null) {
			throw new IllegalArgumentException ("content null is illegal");
		}
		if(authorsMail == null) {
			throw new IllegalArgumentException ("authors mail null is illegal");
		}
		
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

}
