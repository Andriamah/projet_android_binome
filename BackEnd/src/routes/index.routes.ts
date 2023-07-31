import {Router} from 'express';
import{indexWelcome} from '../controller/client.controller'

const router = Router();

router.route('/')
.get(indexWelcome);

export default router;