package com.esseckers.dragndropapp.controller;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.esseckers.dragndropapp.database.DataBaseUtils;
import com.esseckers.dragndropapp.linkedlist.Actor;
import com.esseckers.dragndropapp.linkedlist.ActorsLinkedList;
import com.esseckers.dragndropapp.utils.MockData;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class DataManager {

    public static void fillDatabaseIfEmpty(ContentResolver resolver) {
        MockData.fillDB(resolver);
    }

    public static ActorsLinkedList getActors(ContentResolver resolver) {
        Cursor cursor = resolver.query(DataBaseUtils.ACTOR_CONTENT_URI, null, null, null, null);
        ActorsLinkedList actorsLinkedList = new ActorsLinkedList();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Actor actor = new Actor(cursor.getLong(0), cursor.getString(1));
                actorsLinkedList.add(actor);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return actorsLinkedList;
    }

    public static void update(ContentResolver resolver, ActorsLinkedList list) {
        for (int i = 0; i < list.getSize(); i++) {
            ContentValues cv = new ContentValues();
            cv.put(DataBaseUtils.ACTOR_NAME, list.get(i).getName());
            Uri uri = ContentUris.withAppendedId(DataBaseUtils.ACTOR_CONTENT_URI, i + 1);
            resolver.update(uri, cv, null, null);
        }
    }
}
