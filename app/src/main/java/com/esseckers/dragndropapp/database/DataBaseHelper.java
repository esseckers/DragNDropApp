package com.esseckers.dragndropapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DataBaseUtils.DB_NAME, null, DataBaseUtils.DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseUtils.DB_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
