'use strict';
const mysql = require('mysql');
//local mysql db connection
const dbConn = mysql.createConnection({
  host     : 'mysql-mysqlbase.alwaysdata.net',
  user     : 'mysqlbase',
  password : 'wenna1234',
  database : 'mysqlbase_tourisme'
});
dbConn.connect(function(err) {
  if (err) throw err;
  console.log("Database Connected!");
});
module.exports = dbConn;