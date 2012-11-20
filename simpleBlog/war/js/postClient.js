function PostClient(httpClient) {
	that = {};
	
	that.POST_URL = "post";
	that.POST_TITLE_PROP_NAME = "postTitle";
	that.POST_CONTENT_PROP_NAME = "postContent";
	that.POST_AUTOR_MAIL_PROP_NAME = "postAuthorMail";

	that.httpClient = httpClient;

	that.getAllPostsDetails = function(successHandler, errorHandler) {
		httpClient.get(that.POST_URL, successHandler, errorHandler);
	}

	that.getPostById = function(postId, successHandler, errorHandler) {

		httpClient.get(that.POST_URL + '/' + postId, successHandler,
				errorHandler);

	}

	that.createNewPost = function(title, content, authorsMail, successHandler,
			errorHandler) {

		var settingsData = {};
		settingsData[this.POST_TITLE_PROP_NAME] = title;
		settingsData[this.POST_CONTENT_PROP_NAME] = content;
		settingsData[this.POST_AUTOR_MAIL_PROP_NAME] = authorsMail;

		var settings = {};
		settings['data'] = settingsData;

		httpClient.post(this.POST_URL, successHandler, errorHandler, settings);

	}

	return that;
}
