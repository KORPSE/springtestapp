/**
 * Letter model
 */

(function () {
    
    app.Letter = Backbone.Model.extend({
        url: GLOBAL_URL + "/data/letters/",
        defaults: {
            id: 0,
            number: null,
            date: "",
            subject: "",
            published: false,
            file: null,
            description: null
        },
        initialize: function () {
            //this.listenTo(this, 'change', function () { console.log("cell changed") });
        }

    });
    
}());
