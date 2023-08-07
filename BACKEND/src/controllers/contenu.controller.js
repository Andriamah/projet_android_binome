'use strict'
const Contenu = require('../models/contenu.model')
const Contenu_client_zone = require('../models/Contenu_client_zone.model')

exports.create = function (req, res) {
  const contenu_employee = new Contenu(req.body);
  //handles null error
  if (req.body.constructor === Object && Object.keys(req.body).length === 0) {
    res.status(400).send({ error: true, message: 'Please provide all required field' });
  } else {
    Contenu.create(contenu_employee, function (err, contenu) {
      if (err)
        res.send(err);
      res.json({ error: false, message: "Contenu added successfully!", data: contenu });
      console.log('coucou')
    });
  }
};

exports.findByIdZone = function (req, res) {
  const zoneId = req.params.zoneId;
  Contenu.findByIdZone(zoneId, function (err, contenus) {
    if (err) {
      console.log("error: ", err);
      res.status(500).json({ error: 'Erreur du serveur' });
    } else {
      res.status(200).json(contenus);
    }
  });
};

exports.findById = function (req, res) {
  const zoneId = req.params.zoneId;
  Contenu_client_zone.findById(req.params.id, function (err, contenus) {
    if (err) {
      console.log("error: ", err);
      res.status(500).json({ error: 'Erreur du serveur' });
    } else {
      res.status(200).json(contenus);
    }
  });
};