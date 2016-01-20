package com.esseckers.dragndropapp.model;

/**
 * Created by Fake on 20.01.2016.
 */
public class ContactModel {

    private String id;
    private String name;
    private String photoUrl;

    public ContactModel() {
    }

    public ContactModel(String id, String name, String photoUrl) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
