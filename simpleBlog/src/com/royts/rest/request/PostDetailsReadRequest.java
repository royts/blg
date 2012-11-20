package com.royts.rest.request;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.apache.http.HttpStatus;

import com.royts.PostDetails;
import com.royts.rest.BlgResponse;
import com.royts.storage.Storage;

public class PostDetailsReadRequest implements BlgRequest {

	private Storage storage;

	public PostDetailsReadRequest(Storage storage) {
		this.storage = storage;
	}

	@Override
	public BlgResponse getResponse() {
		List<PostDetails> posts = storage.getPostsDetails(10);
		GenericEntity<List<PostDetails>> entity = new GenericEntity<List<PostDetails>>(posts) {};
		return new BlgResponse(
				HttpStatus.SC_OK,
				entity);

	}

}
