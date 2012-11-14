package com.royts.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import com.royts.Post;

@Path(RestApiPaths.POST)
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

}
