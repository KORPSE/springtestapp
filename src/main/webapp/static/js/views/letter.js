/**
 * Letter view
 */

(function () {
    
    app.LetterView = Backbone.View.extend({

        tagName: "tr",
        
        template: _.template( $('#letter-template').html() ),
        
        events: {
            
        },
        
        initialize: function () {
            this.listenTo(this.model, 'change', this.render);

        },
        
        render: function () {
            this.$el.html( this.template( this.model.toJSON() ) );
            return this;
        }
        
    });
    
})();

