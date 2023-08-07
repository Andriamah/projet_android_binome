'use strict';
const Client = require('../models/client.model');
const bcryptjs = require('bcryptjs');

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
  console.log('update tsika')
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

var dbConn = require('./../../config/db.config');
exports.register = function (req, res) {
  if (req.body.constructor === Object && Object.keys(req.body).length === 0) {
    res.status(400).send({ error: true, message: 'Please provide all required fields' });
  } else {
    const { nom, prenom, pseudo, mail, mdp, pdp } = req.body;

    // Vérifier si toutes les informations requises sont fournies
    if (!nom || !prenom || !pseudo || !mail || !mdp) {
      return res.status(400).json({ error: 'Toutes les informations sont requises' });
    }

    // Vérifier si l'utilisateur existe déjà dans la base de données en fonction du pseudo ou du mail (selon vos besoins)
    dbConn.query("SELECT * FROM client WHERE pseudo = ? OR mail = ?", [pseudo, mail], function (err, results) {
      if (err) {
        console.log("error: ", err);
        res.status(500).json({ error: 'Erreur du serveur' });
      } else if (results.length > 0) {
        res.status(409).json({ error: 'Le pseudo ou l\'adresse e-mail est déjà utilisé' });
      } else {
        // Si l'utilisateur n'existe pas déjà, procéder à l'inscription

        // Étape 1 : Hacher le mot de passe avant de le stocker dans la base de données
        bcryptjs.hash(mdp, 10, function (err, hashedPassword) {
          if (err) {
            console.log("error: ", err);
            res.status(500).json({ error: 'Erreur du serveur' });
          } else {
            // Étape 2 : Créer un nouvel objet Client avec les informations d'inscription
            const newClient = new Client({
              nom,
              prenom,
              pseudo,
              mail,
              mdp: hashedPassword, // Stocker le mot de passe haché
              pdp,
            });

            // Étape 3 : Insérer le nouvel utilisateur dans la base de données
            Client.create(newClient, function (err, clientId) {
              if (err) {
                console.log("error: ", err);
                res.status(500).json({ error: 'Erreur du serveur' });
              } else {
                res.status(201).json({ message: 'Inscription réussie', insertedId: clientId });
              }
            });
          }
        });
      }
    });
  }
};

exports.findById = function (req, res) {
  Client.findById(req.params.id, function (err, notif) {
    if (err)
      res.send(err);
    res.json(notif);
  });
};

exports.login = function (req, res) {
  if (req.body.constructor === Object && Object.keys(req.body).length === 0) {
    res.status(400).json({ error: true, message: 'Please provide all required fields' });
  } else {
    const { pseudo, mdp } = req.body;

    // Vérifier si toutes les informations requises sont fournies
    if (!pseudo || !mdp) {
      return res.status(400).json({ error: 'Pseudo et mot de passe sont requis' });
    }

    // Rechercher le client par pseudo dans la base de données
    Client.findByPseudo(pseudo, function (err, client) {
      if (err) {
        console.log("error: ", err);
        res.status(500).json({ error: 'Erreur du serveur' });
      } else if (!client) {
        // Aucun client trouvé avec ce pseudo
        res.status(401).json({ error: 'Pseudo incorrect' });
      } else {
        // Vérifier le mot de passe en comparant le mot de passe saisi avec le mot de passe haché dans la base de données
        bcryptjs.compare(mdp, client.mdp, function (err, passwordMatch) {
          if (err) {
            console.log("error: ", err);
            res.status(500).json({ error: 'Erreur du serveur' });
          } else if (!passwordMatch) {
            // Mot de passe incorrect
            res.status(401).json({ error: 'Mot de passe incorrect' });
          } else {
            // Authentification réussie
            res.status(200).json({ message: 'Connexion réussie', client });
          }
        });
      }
    });
  }
};

// Ajoutez une nouvelle fonction pour gérer la déconnexion
exports.logout = function (req, res) {
  // Votre logique de déconnexion ici, comme supprimer la session de l'utilisateur, effacer le token JWT, etc.
  // Cela dépend de la méthode d'authentification utilisée dans votre application.

  // Par exemple, si vous utilisez des sessions :
  req.session.destroy(function(err) {
    if (err) {
      console.log("error: ", err);
      res.status(500).json({ error: 'Erreur du serveur' });
    } else {
      res.status(200).json({ message: 'Déconnexion réussie' });
    }
  });
};

