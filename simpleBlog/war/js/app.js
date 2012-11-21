$(document).ready(function() {
	var router = Router();
	var httpClient = HttpClient();
	var postClient = PostClient(httpClient);

	var loadingHtml = new EJS({ url : 'js/templates/loadingTemplate.ejs'}).render();
	
	var showLoading = function () {
		$("#content").html(loadingHtml);
	}
	
	var serveRequest = function(url) {
		
		showLoading();
		
		var page = router.getPage(url, postClient);
		
		page.render($("#content"));
	};

	
	var reRoute = function(event) {
		if (event) {
			serveRequest($(this).attr("href"));
			//event.preventDefault();
		}
	}
	
	serveRequest(document.URL);

	var url = document.URL;

	if (url.indexOf("#") != -1) {
		var splitted = url.split("#");
		url = url[url.lenght - 1];
	}

	$('#homeLink').attr("href", url);

	var oldLocation = location.href;
	setInterval(function() {
		if (location.href != oldLocation) {
			serveRequest(location.href);
			oldLocation = location.href
		}
	}, 1000); // check every second
});
