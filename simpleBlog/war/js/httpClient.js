function HttpClient() {
	
	that = {};
	that.BASIC_REST_URL = "rest/";
	
	//var modulesRoot = "/js/modules/";
	that.get = function(url, successHandler, errorHandler, settings) {
		
		if(settings) {
			this.settings = settings;
		} else {
			this.settings = {};
		}
		
		this.settings["type"] = 'GET';
		this.ajax(url , successHandler, errorHandler, this.settings);

	}

	that.post = function(url, successHandler, errorHandler, settings) {
		
		if(settings) {
			this.settings = settings;
		} else {
			this.settings = {};
		}
		
		this.settings["type"] = 'POST';
		this.ajax(url , successHandler, errorHandler, this.settings);
	}
	
	that.ajax = function (url, successHandler, errorHandler, settings) {
		if(settings) {
			this.settings = settings;
		} else {
			this.settings = {};
		}
		
		this.settings["dataType"] = "json";
        this.settings["url"] = this.BASIC_REST_URL + url;
        this.settings["success"] = successHandler;
        this.settings["error"] = errorHandler;
        
        $.ajax(this.settings);
	}
	

	return that;
}
