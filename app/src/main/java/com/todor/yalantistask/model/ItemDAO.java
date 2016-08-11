package com.todor.yalantistask.model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ItemDAO {

    private static List<Item> filteredItems = new ArrayList<>();
    private static RealmResults<Item> itemsFromDB;
    private static Realm realm = Realm.getDefaultInstance();

    public static List<Item> getItemsForDone() {
        filteredItems.clear();
        realm.executeTransaction(realm1 -> itemsFromDB = realm1
                .where(Item.class)
//                .equalTo("item.state.id", 6)
//                .equalTo("item.state.id", 10)
                .findAll());
        realm.close();

        for (Item item : itemsFromDB) {
            if (item.getState().getId() == 6 | item.getState().getId() == 10) {
                filteredItems.add(item);
            }
        }

        itemsFromDB.deleteAllFromRealm();
        return filteredItems;
    }

    public static List<Item> getItemsForProgress() {
        filteredItems.clear();
        realm.executeTransaction(realm1 -> itemsFromDB = realm1
                .where(Item.class)
//                .equalTo("item.state.id", 0)
//                .equalTo("item.state.id", 5)
//                .equalTo("item.state.id", 7)
//                .equalTo("item.state.id", 8)
//                .equalTo("item.state.id", 9)
                .findAll());
        realm.close();

        for (Item item : itemsFromDB) {
            if (item.getState().getId() == 0 | item.getState().getId() == 5 | item.getState().getId() == 7
                    | item.getState().getId() == 8 | item.getState().getId() == 9) {
                filteredItems.add(item);
            }
        }

        itemsFromDB.deleteAllFromRealm();
        return filteredItems;
    }

    public static List<Item> getItemsForPending() {
        filteredItems.clear();
        realm.executeTransaction(realm1 -> itemsFromDB = realm1
                .where(Item.class)
//                .equalTo("item.state.id", 1)
//                .equalTo("item.state.id", 3)
//                .equalTo("item.state.id", 4)
                .findAll());
        realm.close();

        for (Item item : itemsFromDB) {
            if (item.getState().getId() == 1 | item.getState().getId() == 3 | item.getState().getId() == 4) {
                filteredItems.add(item);
            }
        }

        itemsFromDB.deleteAllFromRealm();
        realm.deleteAll();
        return filteredItems;
    }

}
