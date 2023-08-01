const express = require('express')
const router = express.Router()
const notificationController = require('../controllers/notification.controller')
const historique_notifController = require('../controllers/historique_notification.controller')

// Create a notification
router.post('/', notificationController.create);

// map notif by id
router.get('/:id', historique_notifController.findById);

module.exports = router
