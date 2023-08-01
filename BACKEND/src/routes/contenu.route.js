const express = require('express')
const router = express.Router()

const contenuController = require('../controllers/contenu.controller')

// Create a new contenu
router.post('/', contenuController.create);

router.get('/zone/:zoneId', contenuController.findByIdZone);

module.exports = router
