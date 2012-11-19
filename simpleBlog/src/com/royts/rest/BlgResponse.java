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

	public BlgResponse () {} // for jaxB

	public BlgResponse(int httpStatus, Object data) {
		this.status = httpStatus;
		this.data = data;
	}

	public BlgResponse(int httpStatus) {
		this.status = httpStatus;
	}

	@XmlElement(name = "data")
	public Object getData() {
		return this.data;
	}

	public Response getResponse() {
		return Response.status(this.status).entity(this.data).build();
	}

}
