var express = require('express')
var app = express()

var scb = require('./scb');

var PORT = process.env.NODE_PORT || 3000

app.get('/', function (req, res) {
  	res.send('hello hack for sweden!')
})

app.get('/scb', function(req, res) {
	scb.get(function(err, resp, body) {
		console.log(resp);
		res.send(resp.body);
	});
})

app.listen(PORT, function () {
  console.log('App listening on port', PORT)
})