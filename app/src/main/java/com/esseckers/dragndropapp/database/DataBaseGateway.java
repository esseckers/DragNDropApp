package com.esseckers.dragndropapp.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.esseckers.dragndropapp.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseGateway {
    public static DataBaseGateway instance;
    private DataBaseHelper databaseHelper = DataBaseHelper.getInstance();

    public static DataBaseGateway getInstance() {
        if (instance == null) {
            instance = new DataBaseGateway();
        }
        return instance;
    }

    /**
     * get all contacts
     *
     * @return collection of the {@link ContactModel}
     */
    public List<ContactModel> getContacts() {
        List<ContactModel> contactModels = new ArrayList<ContactModel>();
        String sql = "SELECT * FROM " + DataBaseUtils.CONTACTS_TABLE;
        Cursor cursor = databaseHelper.getDataBase().rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                contactModels.add(DataBaseUtils.parseContactModelCursor(cursor));
            } while (cursor.moveToNext());
        }
        closeCursor(cursor);
        return contactModels;
    }

    public boolean sortContacts(List<ContactModel> contactModels) {
        if (contactModels == null || contactModels.isEmpty()) return false;
        for (ContactModel contactModel : contactModels) {
            insertOrUpdateContacts(contactModel);
        }
        return true;
    }

    private void insertOrUpdateContacts(ContactModel contactModel) {
        SQLiteDatabase dataBase = databaseHelper.getDataBase();
        Cursor cursor = dataBase.rawQuery("SELECT * FROM " + DataBaseUtils.CONTACTS_TABLE, null);
        if (cursor == null || !cursor.moveToFirst()) {
            //insert new
            dataBase.insertWithOnConflict(DataBaseUtils.CONTACTS_TABLE, null, DataBaseUtils.getCV(contactModel), SQLiteDatabase.CONFLICT_REPLACE);
        } else {
            //update existing
            dataBase.updateWithOnConflict(DataBaseUtils.CONTACTS_TABLE, DataBaseUtils.getCV(contactModel), DataBaseUtils.ID + " = " + contactModel.getId(), null, SQLiteDatabase.CONFLICT_REPLACE);
        }
        closeCursor(cursor);

    }

    /**
     * Helper-method for close cursor.
     *
     * @param cursor instance of {@link Cursor}
     */
    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
