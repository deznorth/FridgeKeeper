package com.deznorth.fridgekeeper;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "items")
public class FridgeItem {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "Item_Name")
    public String name;
    @ColumnInfo(name = "Item_Date")
    public String date;
    @ColumnInfo(name = "Item_Adder")
    public String adder;
    @ColumnInfo(name = "Item_Food_Type")
    public int ftype;
    @ColumnInfo(name = "Item_Date_Type")
    public int dateType;

    public FridgeItem( String name, String date, String adder,int ftype, int dateType) {
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
        this.ftype = ftype;
        this.dateType = dateType;
    }

    @Override
    public String toString() {
        return name;
    }

    //SETTER METHODS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAdder(String adder) {
        this.adder = adder;
    }

    public void setFtype(int ftype) {
        this.ftype = ftype;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }


    //GETTER METHODS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getAdder() {
        return adder;
    }

    public int getFtype() {
        return ftype;
    }

    public int getDateType() {
        return dateType;
    }
}
