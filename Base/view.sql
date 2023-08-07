create view historique_notif as
select n.id as id,
c.id as id_reagisseur,
co.id_client as id_publieur,
co.id as id_contenu,
c.pseudo as pseudo,
n.date_notif as date_notif
 from notification n 
join client c 
on c.id = n.id_client
join contenu co 
on co.id = n.id_contenu;

create view historique_favori as
select f.id as id,
f.id as id_reagisseur_fav,
co.id_client as id_publieur,
co.id as id_contenu,
c.pseudo as pseudo,
co.date_contenu as date_contenu
from favori f
join contenu co 
on co.id = f.id_contenu
join client c 
on c.id = co.id_client;

create view contenu_client_zone as
select c.*,
cl.pseudo as pseudo,
z.intitule as intitule_zone
 from contenu c 
join client cl 
on cl.id = c.id_client
join zone z 
on z.id = c.id_zone;

L’objectif principal de ce proje est de créer un application 
mobile concentrée sur la mise  en valeur du tourisme de Madagascar.
Explicitement , le projet consiste à ajouter des contenus vidéo, image ainsi que des description 
Ainsi , les résultats attendues  de ce projet seront : 
- Login , inscription et deconnexion
- Ajout contenus avec image et video
-Acceder à la liste des contenus par zone
-Pouvoir réagir au contenu publier
-Recevoir des notifications

