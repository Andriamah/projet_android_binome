'use strict';

var dbConn = require('./../../config/db.config');

var Notification = function(notification) {
    this.id = notification.id;
    this.id_client = notification.id_client;
    this.id_contenu = notification.id_contenu;
    this.date_notif = notification.date_notif
}

Notification.create = function (newNotif, result) {
dbConn.query("INSERT INTO notification set ?", newNotif, function (err, res) {
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

module.exports = Notification