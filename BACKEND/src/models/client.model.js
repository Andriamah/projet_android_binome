'use strict';
var dbConn = require('./../../config/db.config');
//Employee object create
var Client = function (client) {
    this.id = client.id;
    this.nom = client.nom;
    this.prenom = client.prenom;
    this.pseudo = client.pseudo;
    this.mail = client.mail;
    this.mdp = client.mdp;
    this.pdp = client.pdp;
};

// Client.create = function (newEmp, result) {
// dbConn.query("INSERT INTO employees set ?", newEmp, function (err, res) {
// if(err) {
//   console.log("error: ", err);
//   result(err, null);
// }
// else{
//   console.log(res.insertId);
//   result(null, res.insertId);
// }
// });
// };





Client.findAll = function (result) {
    dbConn.query("Select * from client", function (err, res) {
        if (err) {
            console.log("error: ", err);
            result(null, err);
        }
        else {
            console.log('clients : ', res);
            result(null, res);
        }
    });
};


Client.update = function(id, client, result){
dbConn.query("UPDATE client SET nom=?,prenom=?,pseudo=?,mail=?,mdp=?,pdp=? WHERE id = ?", [client.nom,client.prenom,client.pseudo,client.mail,client.mdp,client.pdp, id], function (err, res) {
if(err) {
  console.log("error: ", err);
  result(null, err);
}else{
  result(null, res);
}
});
};

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


module.exports = Client;