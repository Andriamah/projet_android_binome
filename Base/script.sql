CREATE DATABASE tourisme;
USE tourisme;

CREATE TABLE `client` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nom` varchar(200) NOT NULL,
	`prenom` varchar(200) NOT NULL,
	`pseudo` varchar(100) NOT NULL,
	`mail` varchar(300) NOT NULL,
	`mdp` varchar(10) NOT NULL,
	`pdp` varchar(200) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `zone` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`intitule` varchar(200) NOT NULL,
	`commentaire` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `contenu` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_client` INT NOT NULL,
	`id_zone` INT NOT NULL,
	`commentaire` TEXT NOT NULL,
	`photo` varchar(300) NOT NULL,
	`video` varchar(300) NOT NULL,
	`date_contenu` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `notification` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_client` INT NOT NULL,
	`id_contenu` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `favori` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_client` INT NOT NULL,
	`id_contenu` INT NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `contenu` ADD CONSTRAINT `contenu_fk0` FOREIGN KEY (`id_client`) REFERENCES `client`(`id`);

ALTER TABLE `contenu` ADD CONSTRAINT `contenu_fk1` FOREIGN KEY (`id_zone`) REFERENCES `zone`(`id`);

ALTER TABLE `notification` ADD CONSTRAINT `notification_fk0` FOREIGN KEY (`id_client`) REFERENCES `client`(`id`);

ALTER TABLE `notification` ADD CONSTRAINT `notification_fk1` FOREIGN KEY (`id_contenu`) REFERENCES `contenu`(`id`);

ALTER TABLE `favori` ADD CONSTRAINT `favori_fk0` FOREIGN KEY (`id_client`) REFERENCES `client`(`id`);

ALTER TABLE `favori` ADD CONSTRAINT `favori_fk1` FOREIGN KEY (`id_contenu`) REFERENCES `contenu`(`id`);

ALTER TABLE `client` CHANGE `mdp` `mdp` VARCHAR(200) ;



