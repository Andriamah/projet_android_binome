"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// database.ts
const typeorm_1 = require("typeorm");
const client_1 = require("./entity/client");
(0, typeorm_1.createConnection)({
    type: 'mysql',
    host: 'localhost',
    port: 3306,
    username: 'root',
    password: 'root',
    database: 'tourisme',
    entities: [client_1.Client],
    synchronize: true,
}).then(connection => {
    console.log('Connecté à la base de données MySQL');
}).catch(error => {
    console.error('Erreur de connexion à la base de données :', error);
});
