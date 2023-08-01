'use strict'

var dbConn = require('./../../config/db.config');

var Historique_notification = function(historique_notification){
    // id | id_reagisseur | id_publieur | id_contenu | pseudo | date_notif
    this.id = historique_notification.id;
    this.id_reagisseur = historique_notification.id_reagisseur
    this.id_publieur = historique_notification.id_publieur
    this.id_contenu = historique_notification.id_contenu
    this.pseudo = historique_notification.pseudo
    this.date_notif = historique_notification.date_notif

}

Historique_notification.findById = function (id, result) {
dbConn.query("Select * from historique_notif where id_publieur = ? ", id, function (err, res) {
if(err) {
  console.log("error: ", err);
  result(err, null);
}
else{
  result(null, res);
}
});
};

module.exports = Historique_notification
