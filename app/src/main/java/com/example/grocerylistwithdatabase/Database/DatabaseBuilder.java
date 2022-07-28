package com.example.grocerylistwithdatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.grocerylistwithdatabase.DAO.GroceriesDAO;
import com.example.grocerylistwithdatabase.Entity.Groceries;

@Database(entities = {Groceries.class}, version = 5, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract GroceriesDAO GroceriesDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseBuilder.class, "Database.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}