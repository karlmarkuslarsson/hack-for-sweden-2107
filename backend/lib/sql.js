const alasql = require("alasql");

exports.magic = function (query, data) {
    return query ? alasql(query, data) : data;
};