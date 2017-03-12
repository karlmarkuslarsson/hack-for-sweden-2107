"use strict";

const express = require('express');
const app = express();

const events = require('./lib/events');
const trip_events = require('./lib/trip_events');
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

app.get('/trip', function (req, res) {
    trip_events.get(function (err, data) {
        res.send(data);
    });
});


app.get('/trip-debug', function (req, res) {
    trip_events.get(function (err, data) {
        const eventsHtml = data.events.map(evt => {
            return [
                "<div>",
                "<table>",
                "<tr>",
                "<td>",
                `<img height='150' width='150' src='${evt.img}' />`,
                "</td>",
                `<td><img src='https://maps.googleapis.com/maps/api/staticmap?center=${evt.lat},${evt.lng}&markers=size:mid%7Ccolor:red%7C${evt.lat},${evt.lng}&zoom=13&size=150x150&maptype=roadmap&key=AIzaSyDwdPGDfMfoezcfjtgDiFmmEYJn7N43rl0' /></td>`,
                "<td>",
                `<h3>${evt.title}</h3>`,
                `<p>${evt.description}</p>`,
                "</td>",
                "</tr>",
                "</table>",
                "</div>"
            ].join('');
        });

        const restaurantsHtml = data.restaurants.map(evt => {
            return [
                "<div>",
                "<table>",
                "<tr>",
                "<td>",
                `<img height='150' width='150' src='${evt.img}' />`,
                "</td>",
                `<td><img src='https://maps.googleapis.com/maps/api/staticmap?center=${evt.lat},${evt.lng}&markers=size:mid%7Ccolor:red%7C${evt.lat},${evt.lng}&zoom=13&size=150x150&maptype=roadmap&key=AIzaSyDwdPGDfMfoezcfjtgDiFmmEYJn7N43rl0' /></td>`,
                "<td>",
                `<h3>${evt.title}</h3>`,
                `<p>${evt.description}</p>`,
                "</td>",
                "</tr>",
                "</table>",
                "</div>"
            ].join('');
        });

        const content = [
            '<html>',
            '<h1>Events</h1>',
            eventsHtml.join(''),
            '<h1>Restaurants</h1>',
            restaurantsHtml.join(''),
            '</html>'
        ];
        res.send(content.join(''))

    });
});

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
    const to = req.query.to;
    const from = req.query.from;

    events.get(to, from, function (err, resp) {
        res.send([
            {
                type: "events",
                events: resp.events.event.map(evt => {
                    return {
                        title: evt.title,
                        description: evt.description ? evt.description.trim() : null,
                        image: evt.image ? evt.image.url : null
                    }
                })
            }
        ]);
    });

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