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

    public static void sort(ContentResolver resolver, Actor actor1, Actor actor2) {
        ContentValues cv1 = new ContentValues();
        cv1.put(DataBaseUtils.ACTOR_NAME, actor1.getName());
        Uri uri1 = ContentUris.withAppendedId(DataBaseUtils.ACTOR_CONTENT_URI, actor1.getId() + 1);
        ContentValues cv2 = new ContentValues();
        cv2.put(DataBaseUtils.ACTOR_NAME, actor2.getName());
        Uri uri2 = ContentUris.withAppendedId(DataBaseUtils.ACTOR_CONTENT_URI, actor2.getId() + 1);
        resolver.update(uri1, cv1, null, null);
        resolver.update(uri2, cv2, null, null);
    }
}
