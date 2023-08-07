'use strict'
const Favori = require('../models/favori.model')
const Historique_favori = require('../models/historique_favori.model')

exports.create = function (req, res) {
  const favori = new Favori(req.body);
  //handles null error
  if (req.body.constructor === Object && Object.keys(req.body).length === 0) {
    res.status(400).send({ error: true, message: 'Please provide all required field' });
  } else {
    Favori.create(favori, function (err, fav) {
      if (err)
        res.send(err);
      res.json({ error: false, message: "favori added successfully!", data: fav });
    });
  }
};

exports.findById = function (req, res) {
  Historique_favori.findById(req.params.id, function (err, notif) {
    if (err)
      res.send(err);
    console.log('ATO ANATY REQUETE MITSY TSIKA***************')
    res.json(notif);
  });
};