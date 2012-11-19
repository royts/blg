package com.royts.rest.request;

import org.apache.http.HttpStatus;

import com.royts.Post;
import com.royts.rest.BlgResponse;
import com.royts.rest.RestApiConsts;
import com.royts.storage.Storage;
import com.royts.storage.StorageException;

public class PostCreateRequest implements BlgRequest {

	private Storage storage;
	private String postTitle;
	private String postContent;
	private String authorsMail;

	public PostCreateRequest(
			Storage storage, 
			String postTitle, 
			String postContent, 
			String authorsMail) {
		this.storage = storage;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.authorsMail = authorsMail;
	}

	@Override
	public BlgResponse getResponse() {
		
		BlgResponse reponse;
		
		if (postTitle == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST, RestApiConsts.POST_CREATE_MESSAGE_NO_TITLE);
		}
		if (postContent == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST, RestApiConsts.POST_CREATE_MESSAGE_NO_CONTENT);
		}
		if (authorsMail == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST, RestApiConsts.POST_CREATE_MESSAGE_NO_AUTHOR_MAIL);
		}

		Post newPost = new Post(new Long(-1), postTitle, postContent, authorsMail);
		
		Post savedPost;
		try {
			
			savedPost = this.storage.savePost(newPost);
			reponse = new BlgResponse(HttpStatus.SC_OK, savedPost);
			
		} catch (StorageException e) {
			
			return new BlgResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		} 
		
		return reponse;
	}

}
