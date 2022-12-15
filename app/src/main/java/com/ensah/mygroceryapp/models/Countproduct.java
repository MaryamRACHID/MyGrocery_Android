package com.ensah.mygroceryapp.models;

public class Countproduct {
    private String name;
    private String Categorie;
    private int count;

    public Countproduct(String nom,String cte,int count){
        this.name=nom;
        this.Categorie=cte;
        this.count=count;
    }
    public Countproduct(String nom,int count){
        this.name=nom;
        this.count=count;
        Categorie=" ";
    }
    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
    public String getCategorie(){
        return Categorie;
    }
    public void setCount(int n){
        this.count=n;
    }
    public  void setname(String name){
        this.name=name;
    }
}
