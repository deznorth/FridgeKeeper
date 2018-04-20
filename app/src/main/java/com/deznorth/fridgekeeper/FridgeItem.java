package com.deznorth.fridgekeeper;

public class FridgeItem {
    public final int id;
    public final String name;
    public final String date;
    public final String adder;

    public FridgeItem(int id, String name, String date, String adder) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.adder = adder;
    }

    @Override
    public String toString() {
        return name;
    }
}
