function PostCreatePage(postClient) {
	that = {};
	that.postClient = postClient;
	
	that.render = function(contentElement) {
		
		var template = new EJS({
			url : 'js/templates/postCreateTemplate.ejs'
		});

		contentElement.html(template.render());
	}

	that.validateValues = function(postTitle, postBody, postAuthor) {
		return true;
	}

	$(document).ready(
			function() {
				$('#btnSave').click(
						function(e) {

							var postTitle = $('#postTitle').val();
							var postBody = $('#postBody').val();
							var postAuthor = $('#authorsMail').val();

							if (that.validateValues(postTitle, postBody,
									postAuthor)) {
								that.postClient.createNewPost(postTitle,
										postBody, postAuthor,
										that.saveSucceeded, that.saveFailed)
							}
						});
			});

	that.saveSucceeded = function() {
		window.location = "";
	}

	that.saveFailed = function(jqXHR, textStatus, errorThrown) {
		alert(jqXHR.responseText);
	}

	return that;
}
