insert into client (nom,prenom,pseudo,mail,mdp,pdp) Values('hanintsoa','Elodie','Lodie','elo@gmail.com','123','pfpf');

insert into zone(intitule,commentaire) Values("Antananarivo" , "Situe au centre , c 'est la capitale");

INSERT INTO `contenu` (`id`, `id_client`, `id_zone`, `commentaire`, `photo`, `video`, `date_contenu`) VALUES ('1', '1', '1', 'Tsara Madagasikara', 'klajsgclvhkbjlnkcgrxtcfgbhjnmkcfvgbhjnk', 'dfg hbjkml,cyvgujnkml,;.cfygvbhjkml,;f ghjkl', '2023-08-01 18:31:26.000000');

ALTER TABLE notification
ADD COLUMN date_notif TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP;