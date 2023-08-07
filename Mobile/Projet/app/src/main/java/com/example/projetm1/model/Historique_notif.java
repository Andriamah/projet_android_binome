package com.example.projetm1.model;

public class Historique_notif {

    private  int id;
    private int id_reagisseur;
    private int id_publieur;
    private int id_contenu;
    private String pseudo;
    private  String date_notif;

    public Historique_notif() {
    }

    public int getId() {
        return id;
    }

    public Historique_notif(int id, int id_reagisseur, int id_publieur, int id_contenu, String pseudo, String date_notif) {
        this.id = id;
        this.id_reagisseur = id_reagisseur;
        this.id_publieur = id_publieur;
        this.id_contenu = id_contenu;
        this.pseudo = pseudo;
        this.date_notif = date_notif;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_reagisseur(int id_reagisseur) {
        this.id_reagisseur = id_reagisseur;
    }

    public void setId_publieur(int id_publieur) {
        this.id_publieur = id_publieur;
    }

    public void setId_contenu(int id_contenu) {
        this.id_contenu = id_contenu;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDate_notif(String date_notif) {
        this.date_notif = date_notif;
    }

    public int getId_reagisseur() {
        return id_reagisseur;
    }

    public int getId_publieur() {
        return id_publieur;
    }

    public int getId_contenu() {
        return id_contenu;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getDate_notif() {
        return date_notif;
    }
}
