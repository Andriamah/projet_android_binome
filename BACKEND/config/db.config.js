'use strict';
const mysql = require('mysql');
//local mysql db connection
const dbConn = mysql.createConnection({
  host     : 'byxsfnswh122v4npkp1q-mysql.services.clever-cloud.com',
  user     : 'u2hqtrsp0abp1typ',
  password : 'dCsrDrAdx9qeWkJEbP75',
  database : 'byxsfnswh122v4npkp1q'
});
dbConn.connect(function(err) {
  if (err) throw err;
  console.log("Database Connected!");
});
module.exports = dbConn;