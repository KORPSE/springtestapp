/**
 * Letter view
 */

(function () {
    
    app.LetterView = Backbone.View.extend({

        tagName: "tr",
        
        template: _.template( $('#letter-template').html() ),
        
        events: {
            "click .removeLink": "removeEvent"
        },
        
        initialize: function () {
            this.listenTo(this, 'change', this.render);
        },
        
        render: function () {
            this.$el.html( this.template( this.model.toJSON() ) );
            this.$el.addClass("dataRow");
            return this;
        },
        
        removeEvent: function () {
        	this.model.destroy();
        	this.remove();
        }
        
    });
    
})();

