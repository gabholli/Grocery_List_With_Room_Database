package com.example.grocerylistwithdatabase.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.grocerylistwithdatabase.Database.Repository;

@Entity(tableName = "groceries")
public class Groceries {
    @PrimaryKey(autoGenerate = true)
    private int groceryId;

    private String groceryName;

    public Groceries(String groceryName) {
        this.groceryName = groceryName;
    }

    public Groceries() {

    }

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    @Override
    public String toString() {
        return "Groceries{" +
                "groceryId=" + groceryId +
                ", groceryName='" + groceryName + '\'' +
                '}';
    }

}
