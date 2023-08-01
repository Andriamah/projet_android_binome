'use strict';
var dbConn = require('./../../config/db.config');
var Zone = function (zone) {
  this.id = zone.id;
  this.intitule = zone.intitule;
  this.commentaire = zone.commentaire;
};

Zone.findAll = function (result) {
  dbConn.query("SELECT * FROM zone", function (err, res) {
    if (err) {
      console.log("error: ", err);
      result(null, err);
    } else {
      result(null, res);
    }
  });
};

Zone.rechercheZone = function(critere, result) {
  // Utilisez LIKE %critere% pour effectuer une recherche partielle
  dbConn.query("SELECT * FROM zone WHERE intitule LIKE ?", [`%${critere}%`], function (err, res) {
    if (err) {
      console.log("error: ", err);
      result(null, err);
    } else {
      result(null, res);
    }
  });
}

module.exports = Zone;
