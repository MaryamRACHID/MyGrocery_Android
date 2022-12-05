package com.ensah.mygroceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Course;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
//        databaseHelper.createCourse(new Course("Course 4","fourth course"));
//
//        databaseHelper.createArticle(new Article("produit5","Un"));
//        databaseHelper.createArticle(new Article("produit6","Kg"));


        databaseHelper.getAllCourses().stream().forEach((c)->{
            System.out.println(String.format("id: %s | Name: %s |  Note: %s ",c.getId(),c.getName(),c.getDescription()));
        });

        databaseHelper.getAllArticles().stream().forEach(a->{
            System.out.println(String.format("id: %s | Name: %s |  Unite: %s ",a.getId(),a.getName(),a.getUnite()));
        });
//        databaseHelper.addArticleToCourse("Course Liste 1","produit 1",2);
//        databaseHelper.addArticleToCourse("Course Liste 1","produit 2",2);
//        databaseHelper.addArticleToCourse("Course Liste 1","produit3",3);

//        databaseHelper.addArticleToCourse("Course Liste 2","produit 1",1);
//        databaseHelper.addArticleToCourse("Course Liste 2","produit4",2);
        databaseHelper.getAllCourseArticles().stream().forEach(ca->{
            System.out.println(String.format("Course_id: %d | Article_id: %d |  Count: %d ",ca.getCourse_id(),ca.getAticle_id(),ca.getCounter()));
        });

         Map<Article,Integer> map = databaseHelper.getArticlesOfCourse("Course Liste 1");
         for(Article a:map.keySet()){
             System.out.println(String.format("id: %s | Name: %s |  Unite: %s  | Count: %d",a.getId(),a.getName(),a.getUnite(),map.get(a)));
         }

        Map<Article,Integer> map2 = databaseHelper.getArticlesOfCourse("Course Liste 2");
        for(Article a:map2.keySet()){
            System.out.println(String.format("id: %s | Name: %s |  Unite: %s  | Count: %d",a.getId(),a.getName(),a.getUnite(),map2.get(a)));
        }

        databaseHelper.getCouresrOfArticle("produit 1").stream().forEach(c->{
            System.out.print( c.getName()+" ");
        });
        System.out.println("");
        databaseHelper.getCouresrOfArticle("produit 2").stream().forEach(c->{
            System.out.print( c.getName()+" ");
        });
//        System.out.println("");
//        databaseHelper.getCouresrOfArticle("produit 3").stream().forEach(c->{
//            System.out.print( c.getName()+" ");
//        });
        System.out.println("");
        databaseHelper.getCouresrOfArticle("produit3").stream().forEach(c->{
            System.out.print( c.getName()+" ");
        });
        System.out.println("");
        databaseHelper.getCouresrOfArticle("produit4").stream().forEach(c->{
            System.out.print( c.getName()+" ");
        });
        System.out.println("");
        databaseHelper.getCouresrOfArticle("produit4").stream().forEach(c->{
            System.out.print( c.getName()+" ");
        });
//
//        //databaseHelper.addArticleToCourse("Course 3","produit 1",2);
//        databaseHelper.deleteArticleToCourse("Course 3","produit 1");
//        databaseHelper.getAllCourseArticles().stream().forEach(ca->{
//            System.out.println(String.format("Course_id: %d | Article_id: %d |  Count: %d ",ca.getCourse_id(),ca.getAticle_id(),ca.getCounter()));
//        });
    }
}