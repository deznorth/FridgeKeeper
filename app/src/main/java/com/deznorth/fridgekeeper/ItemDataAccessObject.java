package com.deznorth.fridgekeeper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ItemDataAccessObject {

    @Query("SELECT * FROM items")
    List<FridgeItem> getAllItems();

    @Insert
    void addItem(FridgeItem item);
}
