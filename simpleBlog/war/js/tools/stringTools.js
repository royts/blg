$(document).ready(function() {
	String.prototype.contains = function(searchFor) {
		return (this.indexOf(searchFor) != -1)
	}
});
