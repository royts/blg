function SinglePostPage() {
	that = {};

	that.render = function() {
		
		var template = new EJS({url: 'js/templates/singlePostTemplate.ejs'});
		
		return template.render();
	}

	return that;
}
