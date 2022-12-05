package com.ensah.mygroceryapp.models;

// ManyToMany avec Article mais avec counter ===> CourseArticle
public class Course {

    private Integer id ;

    private String name ;

    private  String description;


    public Course(int id ,String name , String description){
        this.id=id;
        this.name=name;
        this.description= description;
    }
    public Course(String name , String description){
        this.name=name;
        this.description= description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
