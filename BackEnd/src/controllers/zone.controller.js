'use strict';
const Zone = require('../models/zone.model');

exports.findAll = function (req, res) {
  Zone.findAll(function (err, zones) {
    if (err) {
      console.log("error: ", err);
      res.status(500).json({ error: 'Erreur du serveur' });
    } else {
      res.status(200).json(zones);
    }
  });
};

exports.rechercheZone = function (req, res) {
  const critere = req.params.critere;

  Zone.rechercheZone(critere, function (err, zones) {
    if (err) {
      console.log("error: ", err);
      res.status(500).json({ error: 'Erreur du serveur' });
    } else {
      res.status(200).json(zones);
    }
  });
};

