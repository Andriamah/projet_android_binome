// database.ts
import { createConnection } from 'typeorm';
import { Client } from './entity/client';

createConnection({
  type: 'mysql',
  host: 'localhost',
  port: 3306,
  username: 'root',
  password: 'root',
  database: 'tourisme',
  entities: [Client],
  synchronize: true,
}).then(connection => {
  console.log('Connecté à la base de données MySQL');
}).catch(error => {
  console.error('Erreur de connexion à la base de données :', error);
});
