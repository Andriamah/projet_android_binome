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

