(function () {
    
    app.LettersView = Backbone.View.extend({        
        el: $("#springtestapp"),

        events: {
        	"submit form": "save"
        },
        
        model: app.letters,
        
        initialize: function () {
            this.listenTo(this.model, "sync", this.render);
            this.listenTo(this.model, "add", function () { this.model.fetch(); });
            this.model.fetch();
        },
        
        render: function() {
        	$("#lettersHolder .dataRow").remove();
        	app.letters.each( function (letter) {
        		var view = new app.LetterView( { model: letter } );
        		$("#lettersHolder").append(view.render().el);
        	});
            
            return this;
        },
        
        save: function (e) {
        	e.preventDefault();
        	var data = Backbone.Syphon.serialize(this);
            var item = this.model.create(data, {wait: true});
            if (!item.isValid()) {
            	alert(item.validationError);
            	return;
            }
            function clear_form_elements(ele) {

                $(ele).find(':input').each(function() {
                    switch(this.type) {
                        case 'password':
                        case 'select-multiple':
                        case 'select-one':
                        case 'text':
                        case 'hidden':
                        case 'textarea':
                            $(this).val('');
                            break;
                        case 'checkbox':
                        case 'radio':
                            this.checked = false;
                    }
                });
            }
            $("#fileUpload").val("");
            $("#filenameHolder").html('<a href="#" id="uploadLink">Загрузить</a>');
	        $("#uploadLink").on("click", function(){
	        	$("#fileUpload").click();
       		});
            clear_form_elements("#formRow");
        }
    });
    
})();
