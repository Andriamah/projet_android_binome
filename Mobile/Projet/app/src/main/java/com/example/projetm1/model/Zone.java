package com.example.projetm1.model;

public class Zone {
    private int id;
    private String intitule;
    private String description;


    public Zone(int id, String intitule, String description) {
        this.id = id;
        this.intitule = intitule;
        this.description = description;

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


}

