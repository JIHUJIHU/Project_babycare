package com.example.kimminseong.babyhelper;

/**
 * Created by kimminseong on 2017-11-09.
 */

public class Listview_item {
    private int key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String path;
    private String day;
    private String comment;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String Day) {
        this.day = Day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
