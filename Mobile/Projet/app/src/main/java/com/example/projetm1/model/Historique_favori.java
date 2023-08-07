package com.example.projetm1.model;

public class Historique_favori {
    private int id;
    private int id_reagisseur_fav;
    private int id_publieur;
    private int id_contenu;
    private String pseudo;
    private String date_contenu;

    public Historique_favori() {
    }

    public Historique_favori(int id, int id_reagisseur_fav, int id_publieur, int id_contenu, String pseudo, String date_contenu) {
        this.id = id;
        this.id_reagisseur_fav = id_reagisseur_fav;
        this.id_publieur = id_publieur;
        this.id_contenu = id_contenu;
        this.pseudo = pseudo;
        this.date_contenu = date_contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reagisseur_fav() {
        return id_reagisseur_fav;
    }

    public void setId_reagisseur_fav(int id_reagisseur_fav) {
        this.id_reagisseur_fav = id_reagisseur_fav;
    }

    public int getId_publieur() {
        return id_publieur;
    }

    public void setId_publieur(int id_publieur) {
        this.id_publieur = id_publieur;
    }

    public int getId_contenu() {
        return id_contenu;
    }

    public void setId_contenu(int id_contenu) {
        this.id_contenu = id_contenu;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDate_contenu() {
        return date_contenu;
    }

    public void setDate_contenu(String date_contenu) {
        this.date_contenu = date_contenu;
    }
}
