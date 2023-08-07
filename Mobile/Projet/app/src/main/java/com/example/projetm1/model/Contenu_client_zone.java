package com.example.projetm1.model;

public class Contenu_client_zone {
    private  int id;
    private  int id_client;
    private int id_zone;
    private String commentaire;
    private String photo;
    private String video;
    private String date_contenu;
    private String pseudo;
    private String intitule_zone;

    public Contenu_client_zone() {
    }

    public Contenu_client_zone(int id, int id_client, int id_zone,
                               String commentaire, String photo, String video, String date_contenu, String pseudo, String intitule_zone) {
        this.id = id;
        this.id_client = id_client;
        this.id_zone = id_zone;
        this.commentaire = commentaire;
        this.photo = photo;
        this.video = video;
        this.date_contenu = date_contenu;
        this.pseudo = pseudo;
        this.intitule_zone = intitule_zone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_zone() {
        return id_zone;
    }

    public void setId_zone(int id_zone) {
        this.id_zone = id_zone;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDate_contenu() {
        return date_contenu;
    }

    public void setDate_contenu(String date_contenu) {
        this.date_contenu = date_contenu;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getIntitule_zone() {
        return intitule_zone;
    }

    public void setIntitule_zone(String intitule_zone) {
        this.intitule_zone = intitule_zone;
    }
}
