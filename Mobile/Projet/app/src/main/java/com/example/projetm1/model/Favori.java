package com.example.projetm1.model;

public class Favori {
    int id;
    int id_client;
    int id_contenu;

    public Favori() {
    }

    public Favori(int id, int id_client, int id_contenu) {
        this.id = id;
        this.id_client = id_client;
        this.id_contenu = id_contenu;
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
}
