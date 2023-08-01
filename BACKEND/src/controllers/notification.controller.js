'use strict'
const Notification = require('../models/notification.model')

exports.create = function(req, res) {
    const notification = new Notification(req.body);
    //handles null error
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
      res.status(400).send({ error:true, message: 'Please provide all required field' });
    }else{
    Notification.create(notification, function(err, notif) {
      if (err)
      res.send(err);
      res.json({error:false,message:"notification added successfully!",data:notif});
    });
    }
    };