$(document).ready(function() {
	var router = Router();
	var httpClient = HttpClient();
	var postClient = PostClient(httpClient);
	
	var serveRequest = function(url) {
		var page = router.getPage(url, postClient);
		page.render($("#content"));
	};

	serveRequest(document.URL);

	$('a').click(function(event) {
		
		console.log("clicked");
		serveRequest($(this).attr("href"));
		event.preventDefault();
	});
	
	var url = document.URL;
	
	if(url.indexOf("#") != -1) {
		var splitted = url.split("#");
		url = url[url.lenght - 1];
	}
	
	$('#homeLink').attr("href",url);
});
