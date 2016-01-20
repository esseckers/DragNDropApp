package com.esseckers.dragndropapp.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.esseckers.dragndropapp.model.ContactModel;

public class DataBaseUtils {
    /**
     * static SQLite commands
     */
    public static final String CREATE = " CREATE TABLE IF NOT EXISTS ";

    /**
     * Table names constants
     */
    public static final String CONTACTS_TABLE = "contacts_table";

    /**
     * Table columns name
     */
    //universal colums
    public static final String ID = "id";
    //contacts columns
    public static final String PHOTO_URL = "url";
    public static final String NAME = "name";

    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    static ContentValues getCV(ContactModel contactModel) {
        ContentValues cv = new ContentValues();
        cv.put(ID, contactModel.getId());
        cv.put(NAME, contactModel.getName());
        cv.put(PHOTO_URL, contactModel.getPhotoUrl());
        return cv;
    }

    static ContactModel parseContactModelCursor(Cursor cursor) {
        ContactModel resource = new ContactModel();
        resource.setId(cursor.getString(cursor.getColumnIndex(ID)));
        resource.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        resource.setPhotoUrl(cursor.getString(cursor.getColumnIndex(PHOTO_URL)));
        return resource;
    }
}
