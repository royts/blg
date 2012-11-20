describe("Post client tests", function() {
		it("get posts by proper url", function() {

			var httpClientStub = {};
			httpClientStub.get = function(url, successHandler, errorHandler, settings) {
				this.url = url;
			};
			
			var client = PostClient(httpClientStub);
			
			var posts = client.getAllPostsDetails();
			
			expect(httpClientStub.url).toBe("post");
		});

		it("get single post by proper url", function() {

			var httpClientStub = {};
			httpClientStub.get = function(url, successHandler, errorHandler, settings) {
				this.url = url;
				this.settings = settings;
			};
			
			var client = PostClient(httpClientStub);
			
			var postId = '1010';
			var posts = client.getPostById(postId);
			
			expect(httpClientStub.url).toBe("post/1010");
		});
		
		it("create post in proper way", function() {

			var httpClientStub = {};
			httpClientStub.post = function(url, successHandler, errorHandler, settings) {
				this.url = url;
				this.settings = settings;
			};
			
			var client = PostClient(httpClientStub);
			
			var postTitle = 'my title';
			var postContent = 'my content';
			var postAuthorMail = 'author@mail';
			
			var posts = client.createNewPost(postTitle, postContent, postAuthorMail);
			
			expect(httpClientStub.url).toBe("post");
			expect(httpClientStub.settings['data']['postTitle']).toBe(postTitle);
			expect(httpClientStub.settings['data']['postContent']).toBe(postContent);
			expect(httpClientStub.settings['data']['postAuthorMail']).toBe(postAuthorMail);
		});


	});