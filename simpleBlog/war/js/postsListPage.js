function PostsListPage() {
	that = {};

	that.render = function() {
		
		var template = new EJS({url: 'js/templates/postsListTemplate.ejs'});
		
		return template.render();
	}

	return that;
}
