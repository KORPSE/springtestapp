(function () {
    
    app.LettersView = Backbone.View.extend({        
        el: $("springtestapp"),
        initialize: function () {
            this.listenTo(app.letters, 'reset', this.render);
            app.letters.fetch();
        },
        render: function() {
            
        	app.letters.each( function (letter) {
        		var view = new app.LetterView( { model: letter } );
        		$("#lettersHolder").append(view.render().el);
        	});
            
            return this;
        },
    });
    
})();
