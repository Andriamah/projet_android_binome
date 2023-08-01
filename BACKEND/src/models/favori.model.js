'use strict';

var dbConn = require('./../../config/db.config')

var Favori = function(favori) {
    this.id = favori.id;
    this.id_client = favori.id_client;
    this.id_contenu = favori.id_contenu
}

Favori.create = function (newFav, result) {
dbConn.query("INSERT INTO favori set ?", newFav, function (err, res) {
if(err) {
  console.log("error: ", err);
  result(err, null);
}
else{
  console.log(res.insertId);
  result(null, res.insertId);
}
});
};


module.exports = Favori