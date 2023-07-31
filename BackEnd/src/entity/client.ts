import {Column, Entity, PrimaryGeneratedColumn} from "typeorm"
import {IsEmail, IsString} from "class-validator"

@Entity()
export class Client {

    @PrimaryGeneratedColumn()
    id: number

    @Column()
    nom: string

    @Column()
    @IsString()
    prenom: string

    @Column()
    @IsString()
    pseudo: string

    @Column()
    @IsEmail()
    mail: string

    @Column()
    mdp: string

    @Column()
    pdp: string
}
