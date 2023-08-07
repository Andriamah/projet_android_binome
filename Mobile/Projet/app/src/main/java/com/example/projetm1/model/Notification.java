package com.example.projetm1.model;

public class Notification {
    int id;
    int id_client;
    int id_contenu;
    String date_notif;

    public Notification() {
    }

    public Notification(int id, int id_client, int id_contenu, String date_notif) {
        this.id = id;
        this.id_client = id_client;
        this.id_contenu = id_contenu;
        this.date_notif = date_notif;
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

    public int getId_contenu() {
        return id_contenu;
    }

    public void setId_contenu(int id_contenu) {
        this.id_contenu = id_contenu;
    }

    public String getDate_notif() {
        return date_notif;
    }

    public void setDate_notif(String date_notif) {
        this.date_notif = date_notif;
    }
}
