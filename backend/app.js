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
app.get('/practical', function (req, res) {
    const currency = must(req, res, "currency", "currency USD, NOK, DKK, ...");
    const out = [
        {
            type: "phrase",
            phrases: [
                "Hello -> Hej",
                "Bye -> Hejdå"
            ]
        },
        {
            type: "holidays",
            holidays: [
                {
                    weekday: "sunday",
                    date: "2017-01-02",
                    name: "Juldagen"
                },
                {
                    weekday: "saturday",
                    date: "2017-01-01",
                    name: "Julafton"
                }
            ]
        },
        {
            type: "currency",
            text: "1 " + currency + " = 42 SEK"
        }
    ];
    res.send(out);
});

app.get('/todo', function (req, res) {
    const date = req.query.date;
    const out = [
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
    ];
    res.send(out);
});

app.get('/about', function (req, res) {
    const out = [
        {
            type: "event",
            date: "0000-00-00",
            title: "Concert 1"
        }
    ];
    res.send(out);
});

app.get('/all', function (req, res) {
    const out = [
        {
            text: "stuff.."
        }
    ];
    res.send(out);
});

app.listen(PORT, function () {
  console.log('App started on port', PORT);
  console.log(`http://localhost:${PORT}/`);
});