"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.clientRouter = void 0;
// controllers/user.controller.ts
const express_1 = require("express");
const client_service_1 = require("../service/client.service");
exports.clientRouter = (0, express_1.Router)();
const clientService = new client_service_1.ClientService();
// Route to get all users
exports.clientRouter.get('/users', (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const users = yield clientService.getAllUsers();
    res.json(users);
}));
exports.default = exports.clientRouter;
// Other routes and controller functions...
