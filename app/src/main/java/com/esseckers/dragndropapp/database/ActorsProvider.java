package com.esseckers.dragndropapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class ActorsProvider extends ContentProvider {
    DataBaseHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        dbHelper = new DataBaseHelper(getContext());
        return true;
    }

    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // check Uri
        switch (DataBaseUtils.uriMatcher.match(uri)) {
            case DataBaseUtils.URI_ACTORS:
                break;
            case DataBaseUtils.URI_ACTOR_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                // add the ID to the sample condition
                if (TextUtils.isEmpty(selection)) {
                    selection = DataBaseUtils.ACTOR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + DataBaseUtils.ACTOR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DataBaseUtils.ACTORS_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        //notify  contentResolver the cursor of changes in data CONTACT_CONTENT_URI
        if (getContext() != null && getContext().getContentResolver() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(),
                    DataBaseUtils.ACTOR_CONTENT_URI);
        }
        return cursor;
    }

    public Uri insert(@NonNull Uri uri, ContentValues values) {
        if (DataBaseUtils.uriMatcher.match(uri) != DataBaseUtils.URI_ACTORS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(DataBaseUtils.ACTORS_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(DataBaseUtils.ACTOR_CONTENT_URI, rowID);
        // notify ContentResolver, that the data at resultUri changed
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(resultUri, null);
        }
        return resultUri;
    }

    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        switch (DataBaseUtils.uriMatcher.match(uri)) {
            case DataBaseUtils.URI_ACTORS:
                break;
            case DataBaseUtils.URI_ACTOR_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = DataBaseUtils.ACTOR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + DataBaseUtils.ACTOR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(DataBaseUtils.ACTORS_TABLE, selection, selectionArgs);
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cnt;
    }

    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (DataBaseUtils.uriMatcher.match(uri)) {
            case DataBaseUtils.URI_ACTORS:
                break;
            case DataBaseUtils.URI_ACTOR_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    selection = DataBaseUtils.ACTOR_ID + " = " + id;
                } else {
                    selection = selection + " AND " + DataBaseUtils.ACTOR_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(DataBaseUtils.ACTORS_TABLE, values, selection, selectionArgs);
        if (getContext() != null && getContext().getContentResolver() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cnt;
    }

    public String getType(@NonNull Uri uri) {
        switch (DataBaseUtils.uriMatcher.match(uri)) {
            case DataBaseUtils.URI_ACTORS:
                return DataBaseUtils.ACTOR_CONTENT_TYPE;
            case DataBaseUtils.URI_ACTOR_ID:
                return DataBaseUtils.ACTOR_CONTENT_ITEM_TYPE;
        }
        return null;
    }
}
