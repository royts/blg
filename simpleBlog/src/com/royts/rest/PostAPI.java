package com.royts.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("moshe")
public class PostAPI {
	
	@GET
	public String test() {
		return "works";
	}

}
