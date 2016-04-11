package com.esseckers.dragndropapp.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.esseckers.dragndropapp.database.DataBaseUtils;
import com.esseckers.dragndropapp.linkedlist.Actor;
import com.esseckers.dragndropapp.linkedlist.ActorsLinkedList;

/**
 * Created by Ivan Danilov on 11.04.2016.
 * Email: esseckers@gmail.com
 */
public class MockData {

    public static ActorsLinkedList getMockData() {
        String[] mock = "Леонардо ДиКаприо1Марк Руффало1Бен Кингсли1Макс фон Сюдов1Мишель Уильямс1Эмили Мортимер1Патришия Кларксон1Джеки Эрл Хейли1Тед Левайн1Джон Кэрролл Линч1Сергей Бурунов1Илья Хвостиков1Никита Прозоровский1Артем Карапетян1Жанна Никонова".split("1");
        ActorsLinkedList result = new ActorsLinkedList();
        for (String s : mock) {
            result.add(new Actor(s));
        }
        return result;
    }

    public static void fillDB(ContentResolver resolver) {
        Cursor cursor = resolver.query(DataBaseUtils.ACTOR_CONTENT_URI, null, null, null, null);
        if (cursor != null && !cursor.moveToFirst()) {
            ActorsLinkedList actorsLinkedList = MockData.getMockData();
            for (int i = 0; i < actorsLinkedList.getSize(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(DataBaseUtils.ACTOR_NAME, actorsLinkedList.get(i).getName());
                resolver.insert(DataBaseUtils.ACTOR_CONTENT_URI, cv);
            }
            cursor.close();
        }
    }
}
