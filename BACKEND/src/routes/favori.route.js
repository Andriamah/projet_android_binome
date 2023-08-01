const express = require('express')
const router = express.Router()
const favoriController = require('../controllers/favori.controller')

// Create a notification
router.post('/', favoriController.create);

// map notif by id
router.get('/:id', favoriController.findById);

module.exports = router