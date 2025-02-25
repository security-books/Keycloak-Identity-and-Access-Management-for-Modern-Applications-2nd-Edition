var express = require('express');
var Keycloak = require('keycloak-connect');

var app = express();

var keycloak = new Keycloak({});

app.use(keycloak.middleware());

app.get('/hello', keycloak.protect(), function (req, res) {
  res.setHeader('content-type', 'text/plain');
  res.send('Access granted to protected resource');
});

app.listen(3000, function () {
  console.log('Started at port 3000');
});