/**
 * Letter model
 */

(function () {
    
    app.Letter = Backbone.Model.extend({
        urlRoot: GLOBAL_URL + "/data/letters/",
        defaults: {
            number: null,
            date: "",
            subject: "",
            published: false,
            file: null,
            description: null
        },
        initialize: function () {

        },
        
        validate: function (attrs) {
        	
        	if (attrs.number.length == 0) {
        		return "Number must be defined";
        	}

        	if (attrs.date.length == 0) {
        		return "Date must be defined";
        	}
        	
        	if (attrs.file == undefined || attrs.file.id == 0) {
        		return "File must be defined";
        	}
        	
        	if (!/[\d|\+|\-]+/.test(attrs.number)) {
        		return "Field number is incorrect";
        	} else if (!/\d{2}\-\d{2}\-\d{4}/.test(attrs.date)) {
        		return "Field date is incorrect";
        	}
        }
        
    });
    
}());
