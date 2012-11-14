package com.royts.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.royts.Post;
import com.royts.storage.Storage;
import com.royts.storage.StorageFactory;

@Path(RestApiConsts.PATH_POST)
public class PostAPI {

	@Path("/")
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	
	public List<Post> getAllPosts() {
		List<Post> posts = new ArrayList<Post>();
		posts.add(new Post("title", "content", "mail"));
		posts.add(new Post("title", "content", "mail"));
		
		return posts;
	}
	
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getSinglePost(@PathParam("id") final String postId) {
		
		return new BlgResponse(HttpStatus.SC_BAD_GATEWAY,
				"my message", new Post(postId, "one", "one")).getResponse();
		//return new Post(postId, "one", "one");
	}
	
	@Path("/")
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createNewPost(
			@FormParam( RestApiConsts.PARAM_POST_TITLE) String postTitle,
			@FormParam(RestApiConsts.PARAM_POST_CONTENT) String postContent,
			@FormParam(RestApiConsts.PARAM_POST_AUTHHOR_MAIL) String postAuthorMail) {
		
		if(postTitle == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST,RestApiConsts.POST_CREATE_MESSAGE_NO_TITLE).getResponse();
		}
		if(postContent == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST,RestApiConsts.POST_CREATE_MESSAGE_NO_CONTENT).getResponse();
		}
		if(postAuthorMail == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST,RestApiConsts.POST_CREATE_MESSAGE_NO_AUTHOR_MAIL).getResponse();
		}
		
		Post post = new Post (postTitle, postContent, postAuthorMail);
		
		Storage storage = StorageFactory.get();
		
		storage.savePost (post);
		

		return new BlgResponse(HttpStatus.SC_OK, RestApiConsts.POST_CREATE_MESSAGE_CREATED_SUCCESS).getResponse();
	}

}
