// app/services/user.service.ts
import { getRepository } from 'typeorm';
import { Client } from '../entity/client';

export class ClientService {
  private userRepository = getRepository(Client);

  async getAllUsers(): Promise<Client[]> {
    return this.userRepository.find();
  }

}
