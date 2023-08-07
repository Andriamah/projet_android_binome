package com.example.projetm1.model;

public class Zone {
    private int id;
    private String intitule;
    private String description;
    private int imageResourceId;

    public Zone(int id, String intitule, String description, int imageResourceId) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

