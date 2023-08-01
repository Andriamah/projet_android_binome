'use strict';
var dbConn = require('./../../config/db.config');
//Contenu object create
var Contenu = function (contenu) {
    this.id = contenu.id;
    this.id_client = contenu.id_client;
    this.id_zone = contenu.id_zone;
    this.commentaire = contenu.commentaire;
    this.photo = contenu.photo;
    this.video = contenu.video;
    this.date_contenu = contenu.date_contenu;
};

Contenu.create = function (newEmp, result) {
dbConn.query("INSERT INTO contenu set ?", newEmp, function (err, res) {
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


// Client.findById = function (id, result) {
// dbConn.query("Select * from employees where id = ? ", id, function (err, res) {
// if(err) {
//   console.log("error: ", err);
//   result(err, null);
// }
// else{
//   result(null, res);
// }
// });
// };


// Client.findAll = function (result) {
//     dbConn.query("Select * from client", function (err, res) {
//         if (err) {
//             console.log("error: ", err);
//             result(null, err);
//         }
//         else {
//             console.log('clients : ', res);
//             result(null, res);
//         }
//     });
// };


// Client.update = function(id, client, result){
// dbConn.query("UPDATE client SET nom=?,prenom=?,pseudo=?,mail=?,mdp=?,pdp=? WHERE id = ?", [client.nom,client.prenom,client.pseudo,client.mail,client.mdp,client.pdp, id], function (err, res) {
// if(err) {
//   console.log("error: ", err);
//   result(null, err);
// }else{
//   result(null, res);
// }
// });
// };

// Client.delete = function(id, result){
// dbConn.query("DELETE FROM employees WHERE id = ?", [id], function (err, res) {
// if(err) {
//   console.log("error: ", err);
//   result(null, err);
// }
// else{
//   result(null, res);
// }
// });
// };
Contenu.findByIdZone = function (zoneId, result) {
  dbConn.query("SELECT * FROM contenu WHERE id_zone = ?", [zoneId], function (err, res) {
    if (err) {
      console.log("error: ", err);
      result(null, err);
    } else {
      result(null, res);
    }
  });
};

module.exports = Contenu;