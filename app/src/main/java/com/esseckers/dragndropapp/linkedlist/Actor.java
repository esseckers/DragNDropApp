package com.esseckers.dragndropapp.linkedlist;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class Actor {
    private long id;
    private String name;

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public Actor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
