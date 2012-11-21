function SinglePostPage(postClient, postId) {
	that = {};
	that.postClient = postClient;
	that.postId = postId;

	that.render = function(contentElement) {
		this.contentElement = contentElement;
		this.postClient.getPostById(this.postId, this.loadSucceeded,
				this.loadFailed);
	}

	that.loadSucceeded = function(data) {
		var template = new EJS({
			url : 'js/templates/singlePostTemplate.ejs'
		});
		var postData = {
				'post' : data,
		}
		that.contentElement.html(template.render(postData));

	}

	that.loadFailed = function(jqXHR, textStatus, errorThrown) {
		alert("error: " + jqXHR.responseText + " ;status: " + textStatus
				+ " ;error thrown: " + errorThrown);
	}

	return that;
}
