package com.royts.rest.request;

import java.util.Date;

import org.apache.http.HttpStatus;

import com.royts.Post;
import com.royts.rest.BlgResponse;
import com.royts.storage.Storage;
import com.royts.storage.StorageException;

public class PostCreateRequest implements BlgRequest {

	private Storage storage;
	private String postTitle;
	private String postContent;
	private String authorsMail;
	private Date createDate;

	public PostCreateRequest(
			Storage storage, 
			String postTitle, 
			String postContent, 
			String authorsMail) {
		this.storage = storage;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.authorsMail = authorsMail;
		this.createDate = new Date();
	}

	@Override
	public BlgResponse getResponse() {
		
		BlgResponse reponse;
		
		if (postTitle == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST);
		}
		if (postContent == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST);
		}
		if (authorsMail == null) {
			return new BlgResponse(HttpStatus.SC_BAD_REQUEST);
		}

		Post newPost = new Post(new Long(-1), postTitle, postContent, authorsMail, this.createDate);
		
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
