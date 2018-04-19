package com.deznorth.fridgekeeper;

public class FridgeItem {
    public final String id;
    public final String content;
    //public final String details;

    public FridgeItem(String id, String content) {
        this.id = id;
        this.content = content;
        //this.details = details;
    }

    @Override
    public String toString() {
        return content;
    }
}
