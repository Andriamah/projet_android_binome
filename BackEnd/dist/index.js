"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
// app.js (or server.js)
const express_1 = __importDefault(require("express"));
const body_parser_1 = __importDefault(require("body-parser"));
require("data-source"); // Import the database connection setup
const client_controller_1 = __importDefault(require("./controller/client.controller"));
const app = (0, express_1.default)();
const PORT = 3000;
app.use(body_parser_1.default.json());
// Use the userController router for handling user-related routes
app.use('/api', client_controller_1.default);
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
