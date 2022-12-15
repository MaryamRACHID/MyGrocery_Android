package com.ensah.mygroceryapp.models;


public class Article {

    private Integer id;
    private String name;
    private  String unite;
    private String categorie;
    private boolean selected=false;
    int count=-1;
    public Article() {
    }

    public Article(String name,String unite,String categorie) {
        this.name = name;
        this.unite =unite;
        this.categorie=categorie;
        id=count+1;
    }
    public Article(int id,String name,String unite,String categorie) {
        this.name = name;
        this.unite =unite;
        this.categorie=categorie;
        this.id=id;
    }
    public Article(int id,String name,String unite) {
        this.name = name;
        this.unite = unite;
        this.id = id;
    }

        public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategorie(){return categorie;}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public boolean getSelectionState(){
        return selected;
    }
    public void setSelectionState(boolean state){
        this.selected=state;
    }
    public String toString(){
        return this.name+"-"+this.categorie;
    }
}
