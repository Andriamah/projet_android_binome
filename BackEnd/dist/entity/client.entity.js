"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Client = void 0;
const typeorm_1 = require("typeorm");
const class_validator_1 = require("class-validator");
let Client = exports.Client = class Client {
};
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)()
], Client.prototype, "id", void 0);
__decorate([
    (0, typeorm_1.Column)()
], Client.prototype, "nom", void 0);
__decorate([
    (0, typeorm_1.Column)(),
    (0, class_validator_1.IsString)()
], Client.prototype, "prenom", void 0);
__decorate([
    (0, typeorm_1.Column)(),
    (0, class_validator_1.IsString)()
], Client.prototype, "pseudo", void 0);
__decorate([
    (0, typeorm_1.Column)(),
    (0, class_validator_1.IsEmail)()
], Client.prototype, "mail", void 0);
__decorate([
    (0, typeorm_1.Column)()
], Client.prototype, "mdp", void 0);
__decorate([
    (0, typeorm_1.Column)()
], Client.prototype, "pdp", void 0);
exports.Client = Client = __decorate([
    (0, typeorm_1.Entity)()
], Client);
