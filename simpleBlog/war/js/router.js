function Router() {
	that = {};

	that.RESOURCE_DELIMITER = "#";
	that.CREATE_PAGE_URL = "new";
	that.VIEW_PAGE_URL_PREFIX = "view/";

	that.getPage = function(url, postClient) {

		if(url.indexOf(this.RESOURCE_DELIMITER) === -1){

			return PostsListPage(postClient);

		} else {
			
			var urlParts = url.split(this.RESOURCE_DELIMITER);
			
			var resourcePath = urlParts[urlParts.length - 1];
			
			if(resourcePath.indexOf(this.CREATE_PAGE_URL) !=  -1) {
				return PostCreatePage(postClient);
			} 
			
			else if( resourcePath.indexOf( this.VIEW_PAGE_URL_PREFIX != -1 ) ){
				
				var postViewParts = resourcePath.split (this.VIEW_PAGE_URL_PREFIX);
				
				if(postViewParts.length > 1) {
					
					var postId = postViewParts[1];
					postId = postId.substring(1);
					return SinglePostPage(postClient,postId);
				}
			}
		}
		
		return PostsListPage(postClient)
	}

	return that;
}
