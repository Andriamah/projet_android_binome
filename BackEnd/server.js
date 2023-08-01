const express = require('express');
const bodyParser = require('body-parser');
// create express app
const app = express();
// Setup server port
const port = process.env.PORT || 5000;
// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }))
// parse requests of content-type - application/json
app.use(bodyParser.json())
// define a root route
app.get('/', (req, res) => {
  res.send("Hello World");
});
// Require employee routes
const clientRoute = require('./src/routes/client.route');
const contenuRoute = require('./src/routes/contenu.route');
const notificationRoute = require('./src/routes/notification.route')
const favoriRoute = require('./src/routes/favori.route')


// using as middleware
app.use('/clients', clientRoute)
app.use('/contenu',contenuRoute)
app.use('/notification',notificationRoute)
app.use('/favori',favoriRoute)

// listen for requests
app.listen(port, () => {
  console.log(`Server is listening on port ${port}`);
});