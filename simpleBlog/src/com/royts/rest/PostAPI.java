package com.royts.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.royts.rest.request.PostCreateRequest;
import com.royts.rest.request.PostDetailsReadRequest;
import com.royts.rest.request.PostReadRequest;
import com.royts.storage.StorageFactory;

@Path(RestApiConsts.PATH_POST)
public class PostAPI {

	//@Path("/")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllPosts() {

		PostDetailsReadRequest request = new PostDetailsReadRequest(StorageFactory.get());

		return request.getResponse().getResponse();
	}

	@Path("{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSinglePost(@PathParam("id") final String postId) {

		PostReadRequest request = new PostReadRequest(StorageFactory.get(), postId);
		return request.getResponse().getResponse();
		
	}

	//@Path("/")
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createNewPost(
			@FormParam(RestApiConsts.PARAM_POST_TITLE) String postTitle,
			@FormParam(RestApiConsts.PARAM_POST_CONTENT) String postContent,
			@FormParam(RestApiConsts.PARAM_POST_AUTHHOR_MAIL) String postAuthorMail) {

		PostCreateRequest request = new PostCreateRequest(
				StorageFactory.get(),
				postTitle,
				postContent,
				postAuthorMail);

		return request.getResponse().getResponse();
	}

}
