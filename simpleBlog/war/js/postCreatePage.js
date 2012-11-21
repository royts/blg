function PostCreatePage(postClient) {
	that = {};
	that.postClient = postClient;

	that.render = function(contentElement) {

		var template = new EJS({
			url : 'js/templates/postCreateTemplate.ejs'
		});

		contentElement.html(template.render());

		$('#btnSave').click(this.submit);
	}

	that.validateValues = function(postTitle, postBody, postAuthor) {
		
		that.cleanErrors();
		
		var isValid = true;
		
		if(postTitle.isEmpty()) {
			isValid = false;
			this.showError("Please add title");
		}
		
		if(postBody.isEmpty()) {
			isValid = false;
			this.showError("Please add post body");
		}
		
		if(postAuthor.isEmpty()) {
			isValid = false;
			this.showError("Please insert author's address");
		}
		
		return isValid;
	}

	that.submit = function() {
		
		var postTitle = $('#postTitle').val();
		var postBody = $('#postBody').val();
		var postAuthor = $('#authorsMail').val();

		if (that.validateValues(postTitle, postBody, postAuthor)) {
			
			that.showWaitingButton();
			
			that.postClient.createNewPost(postTitle, postBody, postAuthor,
					that.saveSucceeded, that.saveFailed);
		}
	}

	that.saveSucceeded = function() {
		window.location = "";
	}

	that.saveFailed = function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
	}
	
	that.showError= function (message) {
		$('#error').append(message);
		$('#error').append("<br/>");
	}
	
	that.cleanErrors = function () {
		$('#error').text("");
	}
	
	that.showWaitingButton = function () {
		$('#btnSave').val("Saving...");
		$('#btnSave').attr("disabled", "disabled");
	}

	return that;
}
