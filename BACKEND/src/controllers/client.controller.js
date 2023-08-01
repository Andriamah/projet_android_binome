'use strict';
const Client = require('../models/client.model');

exports.findAll = function(req, res) {
Client.findAll(function(err, clients) {
  console.log('controller client')
  if (err)
  res.send(err);
  console.log('res', clients);
  res.send(clients);
});
};

// exports.create = function(req, res) {
// const new_employee = new Employee(req.body);
// //handles null error
// if(req.body.constructor === Object && Object.keys(req.body).length === 0){
//   res.status(400).send({ error:true, message: 'Please provide all required field' });
// }else{
// Employee.create(new_employee, function(err, employee) {
//   if (err)
//   res.send(err);
//   res.json({error:false,message:"Employee added successfully!",data:employee});
// });
// }
// };
// exports.findById = function(req, res) {
// Employee.findById(req.params.id, function(err, employee) {
//   if (err)
//   res.send(err);
//   res.json(employee);
// });
// };

exports.update = function(req, res) {
  if(req.body.constructor === Object && Object.keys(req.body).length === 0){
    res.status(400).send({ error:true, message: 'Please provide all required field' });
  }else{
    Client.update(req.params.id, new Client(req.body), function(err, client) {
   if (err)
   res.send(err);
   res.json({ error:false, message: 'Client successfully updated' });
});
}
};

// exports.delete = function(req, res) {
// Employee.delete( req.params.id, function(err, employee) {
//   if (err)
//   res.send(err);
//   res.json({ error:false, message: 'Employee successfully deleted' });
// });
// };