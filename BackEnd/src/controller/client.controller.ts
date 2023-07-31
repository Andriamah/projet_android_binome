// controllers/user.controller.ts
import { Router, Request, Response } from 'express';
import { ClientService } from '../service/client.service';
import { Client } from '../entity/client';

export const clientRouter = Router();
const clientService = new ClientService();

// Route to get all users
clientRouter.get('/users', async (req: Request, res: Response) => {
  const users = await clientService.getAllUsers();
  res.json(users);
});



export default clientRouter;

// Other routes and controller functions...
