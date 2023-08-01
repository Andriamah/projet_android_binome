const express = require('express')
const router = express.Router()
const notificationController = require('../controllers/notification.controller')

// Create a notification
router.post('/', notificationController.create);

module.exports = router
