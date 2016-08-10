package com.todor.yalantistask.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ItemDAO {

    private static List<Item> items = new ArrayList<>();
    private static RealmResults<Item> modelFromDB;

    public static List<Item> getItemsForDone(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modelFromDB = realm.where(Item.class).findAll();
            }
        });
        realm.close();

        for (Item item : modelFromDB) {
            if (item.getState().getId() == 6 | item.getState().getId() == 10) {
                items.add(item);
            }
        }

        return items;
    }

}
