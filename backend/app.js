"use strict";

const express = require('express');
const app = express();

const sql = require('./lib/sql');
const apis = ['befolk', 'inkomst', 'internet'];

const PORT = process.env.NODE_PORT || 3000;

apis.forEach(function (name) {
    const api = require(`./${name}`);
    app.get('/' + name, function(req, res) {
        api.get(function(err, data) {
            sql.magic(req.query.query, res.send(data));
        });
    });
    console.log(`Registering api '${name}' on GET /${name}`);
});

app.get('/', function (req, res) {
    const links = apis.map(s => {
        return `<dt>${s}</dt><dd><a href="/${s}">GET /${s}</a></dd>`;
    }).join('');
    const content = [
        '<html>',
        '<h1>Hello hack for sweden!</h1>',
        '<p>The following apis are available.</p>',
        "<p><img src='http://thecatapi.com/api/images/get?format=src&type=gif'></p>",
        '<h2>APIs</h2>',
        '<dl>',links,'</dl>',
        '</html>'
    ];
    res.send(content.join(''))
});


app.get('/currency', function (req, res) {
    let out = {};
    const query = req.query;
    out['value'] = "1 USD = 42 SEK";
    out['to_currency'] = "SEK";
    out['from_value'] = query.from_value;
    out['from_currency'] = query.from_currency;
    res.send(out);
});


app.listen(PORT, function () {
  console.log('App started on port', PORT);
  console.log(`http://localhost:${PORT}/`);
});