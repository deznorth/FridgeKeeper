package com.deznorth.fridgekeeper;

public class FridgeItem {
    public final int id;
    public final String name;
    public final String date;
    public final String adder;
    public final int ftype;
    public final int dateType;

    public FridgeItem(int id, String name, String date, String adder,int type, int dateType) {
        this.id = id;
        this.name = name;
        switch (dateType){
            case 1:
                this.date = "Expires: "+date;
                break;
            case 2:
                this.date = "Added: "+date;
                break;
                default:
                    this.date = null;
                    break;

        }
        this.adder = adder;
        this.ftype = type;
        this.dateType = dateType;
    }

    @Override
    public String toString() {
        return name;
    }
}
