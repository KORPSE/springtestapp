/**
 * Letter view
 */

(function () {
    
    app.LetterView = Backbone.View.extend({

        tagName: "tr",
        
        template: _.template( $('#letter-template').html() ),
        
        events: {
            "click .removeLink": "removeEvent",
            "change .published": "switchPublished"
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
        },
        
        switchPublished: function (e) {
        	this.model.set( { published: this.$(".published")[0].checked } );
        	this.model.save();
        }
        
    });
    
})();

