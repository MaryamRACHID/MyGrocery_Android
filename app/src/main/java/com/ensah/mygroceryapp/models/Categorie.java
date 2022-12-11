package com.ensah.mygroceryapp.models;

public class Categorie {

    private  Integer id;
    private String name;
    private String  description;
    int count=-1;

    public Categorie(String n,String des){
        this.id=count+1;
        this.name=n;
        this.description=des;
    }
    public String getCategorieName() {
        return name;
    }

    public Integer getCategorieId() {
        return id;
    }

    public String getCategorieDescription() {
        return description;
    }
}
