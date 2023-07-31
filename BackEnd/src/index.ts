// app.js (or server.js)
import express from 'express';
import bodyParser from 'body-parser';
import 'data-source'; // Import the database connection setup
import clientRouter from './controller/client.controller';

const app = express();
const PORT = 3000;

app.use(bodyParser.json());

// Use the userController router for handling user-related routes
app.use('/api', clientRouter);

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
