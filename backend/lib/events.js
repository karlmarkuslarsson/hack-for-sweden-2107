const request = require('request');
const cachedRequest = require('cached-request')(request);

/* Set cache dir */
cachedRequest.setCacheDirectory(".cache/");

/*  */
exports.get = function (to, from, done) {
    const dateRange = (to && from) ? to + "-" + from : false;
    const dateParam = dateRange || "Future";
    const url = [
        "http://api.eventful.com",
        "json/events",
        "search?app_key=wTzCQzDjFmQcJDrj&location=Stockholm&date=" + dateParam
    ].join('/');
    cachedRequest({
        url: url,
        method: "GET"
    }, function (err, resp, data) {
        done(err, data ? JSON.parse(data) : {});
    })
};
