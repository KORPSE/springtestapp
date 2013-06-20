/**
 * Enter-point of app
 */
var GLOBAL_URL = "/springtestapp"
var app = {};

_.templateSettings = {
	interpolate : /\<\@\=(.+?)\@\>/gim,
	evaluate : /\<\@(.+?)\@\>/gim,
	escape : /\<\@\-(.+?)\@\>/gim
};

$(document).ajaxError(function(event, jqxhr, settings, exception) {
	alert(jQuery.parseJSON(jqxhr.responseText).error);
});

$(function () {
	new app.LettersView();
});
