const request = require('request');
const cachedRequest = require('cached-request')(request);
const sql = require('./sql');

/* Set cache dir */
cachedRequest.setCacheDirectory(".cache/");

function as_keyvalue(data) {
    const cols = data.columns
            .map(s => s.text)
            .map(s => s.replace(/ /gi, " ")
                .replace(/,/gi, " ")
                .replace(/  /gi, "_")
                .replace(/ /g, "_")
            );

    return data
        .data
        .map(cell => cell.key.concat(cell.values))
        .map(function (cell) {
            return cell.map(function (v, i) { return [cols[i], v]; });
        })
        .map(function (cell) {
            const a = {};
            cell.forEach(function (itm, idx) {
                a[itm[0].toLocaleLowerCase()] = itm[1];
            });
            return a;
        });
}

function as_keyvalue_resp(done) {
    return function (err, resp, data) {
        const jsonString = (data||'{}');
        const json = JSON.parse(jsonString.trim());
        done(err, as_keyvalue(json));
    }
}

const apis = ['befolk', 'inkomst', 'internet'];

exports.init = function (app) {
    apis.forEach(function (name) {
        const api = require(`./${name}`);
        app.get('/scb/' + name, function(req, res) {
            api.get(function(err, data) {
                sql.magic(req.query.query, res.send(data));
            });
        });
        console.log(`Registering api '${name}' on GET /${name}`);
    });
};

exports.get = function (url, request_data, done) {
    cachedRequest({
        url: url,
        method: "POST",
        json: request_data
    }, as_keyvalue_resp(done));
};



