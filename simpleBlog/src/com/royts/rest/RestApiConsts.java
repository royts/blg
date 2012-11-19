package com.royts.rest;

public class RestApiConsts {

	public static final String PATH_POST = "/post/";
	public static final String PARAM_POST_TITLE = "postTitle";
	public static final String PARAM_POST_CONTENT = "postContent";
	public static final String PARAM_POST_AUTHHOR_MAIL = "postAuthorMail";
	public static final String POST_CREATE_MESSAGE_CREATED_SUCCESS = "Post created succesfully";
	public static final String POST_CREATE_MESSAGE_NO_AUTHOR_MAIL = "post author's mail not found";
	public static final String POST_CREATE_MESSAGE_NO_CONTENT = "post content not found";
	public static final String POST_CREATE_MESSAGE_NO_TITLE = "post title not found";
	
	private RestApiConsts(){
	}
	
}
