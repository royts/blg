package com.royts.rest;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.royts.Post;

@XmlRootElement
@XmlSeeAlso({ Post.class })
public class BlgResponse {

	int status;
	Object data;
	String message;

	public BlgResponse () {} // for jaxB
	
	public BlgResponse(int httpStatus, String message) {
		this(httpStatus, message, null);
	}

	public BlgResponse(int httpStatus, String message, Object data) {
		this.status = httpStatus;
		this.message = message;
		this.data = data;
	}

	@XmlElement(name = "message")
	public String getMessage() {
		return this.message;
	}

	@XmlElement(name = "data")
	public Object getData() {
		return this.data;
	}

	public Response getResponse() {
		return Response.status(this.status).entity(this).build();
	}

}