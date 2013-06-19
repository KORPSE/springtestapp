/**
 * Enter-point of app
 */
var GLOBAL_URL = "/springtestapp"
var app = {};

$(document).ajaxError(function(event, jqxhr, settings, exception) {
	alert(jQuery.parseJSON(jqxhr.responseText).error);
});

$(function () {
	new app.LettersView();
});
