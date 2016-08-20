package com.todor.yalantistask.model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ItemDAO {

    private static List<Item> filteredItems = new ArrayList<>();
    private static Realm realm = Realm.getDefaultInstance();

    public static List<Item> getItemsForDone() {
//        filteredItems.clear();

        realm.executeTransaction(realm1 -> filteredItems = realm1
                .where(Item.class)
                .equalTo("state.id", 6)
                .or()
                .equalTo("state.id", 10)
                .findAll());
        realm.close();

        return filteredItems;
    }

    public static List<Item> saveItems(List<Item> items) {
        filteredItems.clear();
        List<Item> model = new ArrayList<>();
//        realm.executeTransaction(realm1 -> model.addAll(realm1.copyToRealmOrUpdate(items)));
        realm.beginTransaction();
        model.addAll(realm.copyToRealmOrUpdate(items));
        realm.close();

        return model;
    }

}
