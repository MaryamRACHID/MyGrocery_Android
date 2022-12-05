package com.ensah.mygroceryapp.models;


public class Article {

    private Integer id;
    private String name;
    private  String unite;
    private Integer CategorieId;
    public Article() {

    }

    public Article(String name,String unite) {
        this.name = name;
        this.unite =unite;
    }

    public Article(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Article(int id, String name, String unite) {
        this.id = id;
        this.name = name;
        this.unite=unite;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
}
