// controllers/user.controller.ts
import { Router, Request, Response } from 'express';
import { ClientService } from '../service/client.service';
import { Client } from '../entity/client';

export function indexWelcome(req : Request , res : Response):Response{
  return res.json('WElcome to the API');
}

