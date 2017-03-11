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

function must(req, res, key, name) {
    const value = req.query[key];
    return value ? value : res.send(`Missing param '${key}', provide a ${name}!`);
}

// all?currency=USD&date=2017-01-01
app.get('/all', function (req, res) {
    const currency = must(req, res, "currency", "currency USD, NOK, DKK, ...");
    const date = req.query.date;
    const out = {
        practical: [
            {
                type: "phrase",
                phrases: [
                    "Hello -> Hej",
                    "Bye -> Hejdå"
                ]
            },
            {
                type: "holiday",
                weekday: "sunday",
                date: "2017-01-02",
                name: "Juldagen"
            },
            {
                type: "holiday",
                weekday: "saturday",
                date: "2017-01-01",
                name: "Julafton"
            },
            {
                type: "currency",
                text: "1 " + currency + " = 42 SEK"
            }
        ],
        todo: [
            {
                type: "event",
                date: (date || "0000-00-00"),
                title: "Concert 1"
            },
            {
                type: "event",
                date: "2017-02-03",
                title: "Concert 2"
            }
        ]
    };

    res.send(out);
});


// from_currency= from_value to_currency
app.get('/currency', function (req, res) {
    let out = {};
    const query = req.query;
    out['value'] = "1 USD = 42 SEK";
    out['to_currency'] = must(req, res, "to_currency", "currency");
    out['from_value'] = must(req, res, "from_value", "number");
    out['from_currency'] = must(req, res, "from_currency", "currency");
    res.send(out);
});

// date=20170101
app.get('/holidays', function (req, res) {
    let out = {};
    out['date'] = must(req, res, "date", "Date");
    out['holidays'] = [
        {
            date: "20171031",
            date_text: "31 oktober",
            name: "Halloween 2017",
            weekday: "Måndag"
        }
    ];
    res.send(out);
});


app.listen(PORT, function () {
  console.log('App started on port', PORT);
  console.log(`http://localhost:${PORT}/`);
});