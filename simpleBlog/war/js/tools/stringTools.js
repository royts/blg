$(document).ready(function() {
	String.prototype.contains = function(searchFor) {
		return (this.indexOf(searchFor) != -1)
	}
	
	String.prototype.isEmpty = function() {
		return (this.length === 0)
	}
});
