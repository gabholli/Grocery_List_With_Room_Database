package com.example.grocerylistwithdatabase.Database;

import android.app.Application;

import com.example.grocerylistwithdatabase.DAO.GroceriesDAO;
import com.example.grocerylistwithdatabase.Entity.Groceries;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private GroceriesDAO mGroceriesDAO;
    private List<Groceries> mAllGroceries;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        DatabaseBuilder db = DatabaseBuilder.getDatabase(application);
        mGroceriesDAO = db.GroceriesDAO();
    }

    public List<Groceries> getAllGroceries() {
        databaseExecutor.execute(() -> {
            mAllGroceries = mGroceriesDAO.getAllGroceries();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllGroceries;
    }

    public void insert(Groceries groceries) {
        databaseExecutor.execute(() -> {
            mGroceriesDAO.insert(groceries);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Groceries groceries) {
        databaseExecutor.execute(() -> {
            mGroceriesDAO.delete(groceries);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteGroceryByTableIndex(int rowIndex) {
        databaseExecutor.execute(() -> {
            mGroceriesDAO.deleteGroceryByTableIndex(rowIndex);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
