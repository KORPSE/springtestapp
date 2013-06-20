/**
 * Letters collection
 */
(function () {
    var Letters = Backbone.Collection.extend({
        model: app.Letter,
        url: GLOBAL_URL + "/data/letters/",
    });
    
    app.letters = new Letters();
}());