var express = require('express');
var app = express();

var befolk = require('./befolk');

var PORT = process.env.NODE_PORT || 3000

app.get('/', function (req, res) {
  	res.send('Hello hack for sweden!')
})

app.get('/scb', function(req, res) {
	befolk.get(function(err, data) {
		console.log(data);
		res.send(data);
	});
})

app.listen(PORT, function () {
  console.log('App listening on port', PORT)
})