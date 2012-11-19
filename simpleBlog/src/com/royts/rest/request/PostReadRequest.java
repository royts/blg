package com.royts.rest.request;

import org.apache.http.HttpStatus;

import com.royts.Post;
import com.royts.rest.BlgResponse;
import com.royts.storage.Storage;
import com.royts.storage.StorageException;

public class PostReadRequest implements BlgRequest {

	private Storage storage;
	private String postIdString;

	public PostReadRequest(Storage storage, String postIdString) {
		this.storage = storage;
		this.postIdString = postIdString;

	}

	@Override
	public BlgResponse getResponse() {

		if (postIdString == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST);
		}

		Long postId = null;
		try {
			postId = new Long(postIdString);
		} catch (NumberFormatException ex) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST, "request post ID format is invalid");
		}

		BlgResponse response;
		Post post;

		try {
			post = storage.getPostById(postId);
		} catch (StorageException e) {
			return new BlgResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}

		if (post == null) {
			response = new BlgResponse(HttpStatus.SC_NOT_FOUND);
		} else {
			response = new BlgResponse(HttpStatus.SC_OK, post);
		}

		return response;

	}

}
