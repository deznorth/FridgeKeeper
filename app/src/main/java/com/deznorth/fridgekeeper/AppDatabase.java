package com.deznorth.fridgekeeper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {FridgeItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDataAccessObject itemsDao();
}
