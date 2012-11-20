function PostsListPage(postClient) {
	that = {};
	that.contentElement = {};

	that.render = function(contentElement) {
		this.contentElement = contentElement;
		postClient.getAllPostsDetails(that.loadSucceeded, that.loadFailed);
	}
	that.loadSucceeded = function(data) {
		var template = new EJS({
			url : 'js/templates/postsListTemplate.ejs'
		});
		that.contentElement.html(template.render(data));
	}

	that.loadFailed = function(jqXHR, textStatus, errorThrown) {
		alert("error: " + jqXHR.responseText + " ;status: " + textStatus
				+ " ;error thrown: " + errorThrown);
	}

	return that;
}
