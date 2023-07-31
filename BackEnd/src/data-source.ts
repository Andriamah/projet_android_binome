import { createPool } from "mysql2/promise";
import Connection from 'mysql2/typings/mysql/lib/Connection';

export async function connect() {
  const connection = await createPool({
    host : 'localhost',
    user : 'root',
    password : 'root',
    database: 'tourisme',
    connectionLimit : 10
  })

  return connection;
  
}