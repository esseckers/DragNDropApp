package com.esseckers.dragndropapp.database;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class DataBaseUtils {

    //fields
    public static final String ACTOR_ID = "id";
    public static final String ACTOR_NAME = "name";

    static final String DB_NAME = "actors_db";
    static final int DB_VERSION = 1;
    static final String ACTORS_TABLE = "actors";
    static final String DB_CREATE = "create table " + ACTORS_TABLE + "("
            + ACTOR_ID + " integer primary key autoincrement, "
            + ACTOR_NAME + " text );";

    // authority
    static final String AUTHORITY = "com.esseckers.dragndropapp";

    // path
    static final String ACTORS_PATH = "actors";

    // general Uri
    public static final Uri ACTOR_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + ACTORS_PATH);

    // data types
    // lines
    static final String ACTOR_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + ACTORS_PATH;

    // line
    static final String ACTOR_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + ACTORS_PATH;

    // UriMatcher
    // general Uri
    static final int URI_ACTORS = 1;

    // Uri with specified ID
    static final int URI_ACTOR_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ACTORS_PATH, URI_ACTORS);
        uriMatcher.addURI(AUTHORITY, ACTORS_PATH + "/#", URI_ACTOR_ID);
    }
}
