package com.sip.biblio;

import java.io.Serializable;

public class Categorie implements Serializable {

    private String id;
    private String titre;

    public Categorie() {}

    public Categorie(String id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Identifiant: " + id + " - Titre : " + titre;
    }
}
