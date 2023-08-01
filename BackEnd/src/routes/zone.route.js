const express = require('express');
const router = express.Router();
const zoneController = require('../controllers/zone.controller');

// Route pour récupérer toutes les zones
router.get('/', zoneController.findAll);
router.get('/recherche/:critere', zoneController.rechercheZone);

module.exports = router;
