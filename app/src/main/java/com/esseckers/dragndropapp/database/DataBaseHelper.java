package com.esseckers.dragndropapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.esseckers.dragndropapp.TheApplication;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "DRAGnDROP";
    private SQLiteDatabase sqLiteDatabase;
    private static DataBaseHelper instance;

    public static DataBaseHelper getInstance() {
        if (instance == null) {
            instance = new DataBaseHelper(TheApplication.getInstance(), DATABASE_NAME, null);
        }
        return instance;
    }

    public SQLiteDatabase getDataBase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = getWritableDatabase();
        }
        return sqLiteDatabase;
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, VERSION);
        instance = this;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        if (i != i2) {
            deleteTables(sqLiteDatabase);
            createTables(sqLiteDatabase);
        }
    }

    private void createTable(SQLiteDatabase sqLiteDatabase, String table, String... columns) {
        String query = DataBaseUtils.CREATE + table + " (";
        if (columns.length == 1) {
            query += columns[columns.length - 1] + ");";
        } else {
            for (int i = 0; i < columns.length - 1; i++) {
                String column = columns[i];
                query += column + ", ";
            }
            query += columns[columns.length - 1] + ");";
        }
        sqLiteDatabase.execSQL(query);
    }

    private void createTables(SQLiteDatabase sqLiteDatabase) {

        createTable(sqLiteDatabase, DataBaseUtils.CONTACTS_TABLE
                , DataBaseUtils.ID
                , DataBaseUtils.NAME
                , DataBaseUtils.PHOTO_URL);

    }

    private void dropTable(SQLiteDatabase sqLiteDatabase, String table) {
        sqLiteDatabase.execSQL(DataBaseUtils.DROP_TABLE_IF_EXISTS + table);
    }

    private void deleteTables(SQLiteDatabase sqLiteDatabase) {
        dropTable(sqLiteDatabase, DataBaseUtils.CONTACTS_TABLE);
    }
}
