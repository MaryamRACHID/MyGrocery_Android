package com.ensah.mygroceryapp.models;

// ManyToMany avec Course ===> table association UserCourse
public class User {
    private  Integer id;
    private String username;
    private  String name ;
    private  String password;
 public User(String uname,String name,String psswd){
    this.name=name;
    this.username=uname;
    this.password=psswd;
 }
 public User(){

 }
 public String getName(){
     return name;
 }
 public String getUsername(){
     return username;
 }
 public String getPassword(){
     return password;
 }
 public int getId(){
     return id;
 }
}
