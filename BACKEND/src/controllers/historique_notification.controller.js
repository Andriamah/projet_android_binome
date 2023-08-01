'use strict';
const Historique_notification = require('../models/historique_notif.model')

exports.findById = function (req, res) {
    Historique_notification.findById(req.params.id, function (err, notif) {
        if (err)
        res.send(err);
        res.json(notif);
    });
};