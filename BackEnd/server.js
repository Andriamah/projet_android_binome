const express = require('express');
const bodyParser = require('body-parser');
// create express app
const app = express();
// Setup server port
const port = process.env.PORT || 5000;
// parse requests of content-type - application/x-www-form-urlencoded
app.use(express.json());
//app.use(bodyParser.urlencoded({ extended: true }))
// parse requests of content-type - application/json

app.use(bodyParser.json({ limit: '100mb' }));
app.use(bodyParser.urlencoded({ limit: '100mb', extended: true }));


// define a root route
app.get('/', (req, res) => {
  res.send("Hello World");
});
// Require employee routes
const clientRoute = require('./src/routes/client.route');
const contenuRoute = require('./src/routes/contenu.route');
const notificationRoute = require('./src/routes/notification.route')
const favoriRoute = require('./src/routes/favori.route')
const zoneRoute = require('./src/routes/zone.route');


// using as middleware
app.use('/client', clientRoute)
app.use('/contenu',contenuRoute)
app.use('/notification',notificationRoute)
app.use('/favori',favoriRoute)
app.use('/zone', zoneRoute);

// listen for requests
app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});