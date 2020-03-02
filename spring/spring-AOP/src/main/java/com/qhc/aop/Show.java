package com.qhc.aop;

import org.springframework.stereotype.Component;

@Component
public class Show {
    private String title;
    private String actor;

    public Show(String title, String actor) {
        this.title = title;
        this.actor = actor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Show() {
    }

    public void show(){
        System.out.println("一场精彩的表演");
    }
}
