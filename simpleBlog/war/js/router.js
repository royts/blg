function Router() {
	that = {};

	that.RESOURCE_DELIMITER = "#";
	that.CREATE_PAGE_URL = "new";
	that.VIEW_PAGE_URL_PREFIX = "view/";

	that.getPage = function(url, postClient) {

		var pageToReturn;

		if (!url.contains(this.RESOURCE_DELIMITER)) {
			return PostsListPage(postClient);
		}

		if (url.contains(this.CREATE_PAGE_URL)) {
			pageToReturn = PostCreatePage(postClient);
		} else {
			if (url.contains(this.VIEW_PAGE_URL_PREFIX)) {
				pageToReturn = this.createSinglePage(url, postClient);
			} else {
				pageToReturn = PostsListPage(postClient);
			}
		}

		return pageToReturn;
	}

	that.createSinglePage = function(url, postClient) {
		var postId = url.split(this.VIEW_PAGE_URL_PREFIX)[1];
		return new SinglePostPage(postClient, postId);
	}

	return that;
}
