package com.ensah.mygroceryapp.models;

public class CourseArticle {

    Integer aticle_id;
    Integer course_id;
    Integer counter;

    public CourseArticle( Integer course_id,Integer aticle_id, Integer counter) {
        this.aticle_id = aticle_id;
        this.course_id = course_id;
        this.counter = counter;
    }

    public Integer getAticle_id() {
        return aticle_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setAticle_id(Integer aticle_id) {
        this.aticle_id = aticle_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
