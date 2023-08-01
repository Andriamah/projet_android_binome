insert into client (nom,prenom,pseudo,mail,mdp,pdp) Values('hanintsoa','Elodie','Lodie','elo@gmail.com','123','pfpf');

insert into zone(intitule,commentaire) Values("Antananarivo" , "Situe au centre , c 'est la capitale");

ALTER TABLE notification
ADD COLUMN date_notif TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP;