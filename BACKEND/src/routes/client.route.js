const express = require('express')
const router = express.Router()
const clientController =   require('../controllers/client.controller');


// Retrieve all employees
router.get('/', clientController.findAll);
// Create a new employee
// router.post('/', employeeController.create);
// Retrieve a single employee with id

router.get('/:id', clientController.findById);

// Update a employee with id
router.put('/:id', clientController.update);
// Delete a employee with id
// router.delete('/:id', employeeController.delete);

router.post('/register', clientController.register);

router.post('/login', clientController.login);

router.post('/logout', clientController.logout);

module.exports = router