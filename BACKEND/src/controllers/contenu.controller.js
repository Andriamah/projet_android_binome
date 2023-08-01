'use strict'
const Contenu = require('../models/contenu.model')

exports.create = function(req, res) {
const contenu_employee = new Contenu(req.body);
//handles null error
if(req.body.constructor === Object && Object.keys(req.body).length === 0){
  res.status(400).send({ error:true, message: 'Please provide all required field' });
}else{
Contenu.create(contenu_employee, function(err, contenu) {
  if (err)
  res.send(err);
  res.json({error:false,message:"Contenu added successfully!",data:contenu});
});
}
};