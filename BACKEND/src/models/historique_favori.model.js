'use strict'

var dbConn = require('./../../config/db.config')

var Historique_favori = function(historique_favori){
    // id | id_reagisseur_fav | id_publieur | id_contenu | pseudo | date_contenu
    this.id = historique_notification.id;
    this.id_reagisseur_fav = historique_notification.id_reagisseur_fav
    this.id_publieur = historique_notification.id_publieur
    this.id_contenu = historique_notification.id_contenu
    this.pseudo = historique_notification.pseudo
    this.date_contenu = historique_notification.date_contenu
}

Historique_favori.findById = function (id, result) {
    dbConn.query("Select * from historique_favori where id_reagisseur_fav = ? ", id, function (err, res) {
    if(err) {
      console.log("error: ", err);
      result(err, null);
    }
    else{
      result(null, res);
    }
    });
    };
    

module.exports = Historique_favori
