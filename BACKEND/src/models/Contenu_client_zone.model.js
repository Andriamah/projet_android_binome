'use strict'
var dbConn = require('../../config/db.config');

var Contenu_client_zone = function(contenu_client_zone) {
    this.id = contenu_client_zone.id;
    this.id_client = contenu_client_zone.id_client;
    this.id_zone = contenu_client_zone.id_zone;
    this.commentaire = contenu_client_zone.commentaire;
    this.photo = contenu_client_zone.photo;
    this.video = contenu_client_zone.video;
    this.date_contenu = contenu_client_zone.date_contenu;
    this.pseudo = contenu_client_zone.pseudo;
    this.intitule_zone = contenu_client_zone.intitule_zone;
  };

  //detail
  Contenu_client_zone.findById = function (id, result) {
    dbConn.query("Select * from contenu_client_zone where id = ? ", id, function (err, res) {
      if (err) {
        console.log("error: ", err);
        result(err, null);
      }
      else {
        result(null, res[0]);
      }
    });
  };

  module.exports = Contenu_client_zone