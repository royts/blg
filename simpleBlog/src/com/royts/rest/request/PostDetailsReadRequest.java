package com.royts.rest.request;

import java.util.List;

import org.apache.http.HttpStatus;

import com.royts.PostDetails;
import com.royts.rest.BlgResponse;
import com.royts.rest.PostsDetails;
import com.royts.storage.Storage;

public class PostDetailsReadRequest implements BlgRequest {

	private Storage storage;

	public PostDetailsReadRequest(Storage storage) {
		this.storage = storage;
	}

	@Override
	public BlgResponse getResponse() {
		List<PostDetails> posts = storage.getAllPostsDetails();
		PostsDetails postsDetails = new PostsDetails(posts);
		return new BlgResponse(
				HttpStatus.SC_OK,
				postsDetails);

	}

}
