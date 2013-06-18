(function () {
    var Letters = Backbone.Collection.extend({
        model: app.Letter,
        url: GLOBAL_URL + "/data/letters/",
        initialize: function () {
        }
    });
    
    app.letters = new Letters();
}());