package com.example.grocerylistwithdatabase.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.grocerylistwithdatabase.Entity.Groceries;

import java.util.List;

@Dao
public interface GroceriesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Groceries groceries);

    @Delete
    void delete(Groceries groceries);

    @Query("SELECT * FROM groceries ORDER BY groceryId ASC")
    List<Groceries> getAllGroceries();

//    @Query("SELECT * FROM groceries LIMIT 1 OFFSET :rowIndex")
    @Query("DELETE FROM groceries WHERE groceryId in (SELECT groceryId FROM groceries LIMIT 1 OFFSET :rowIndex)")
    //    @Query("SELECT ROW_NUMBER() OVER (ORDER BY groceryId) WHERE groceryId = :rowIndex")
//    @Query("SELECT groceryId, (SELECT COUNT(*) FROM groceries AS t2 WHERE t2.groceryId <= t1.groceryId) AS row_Num FROM groceries AS t1 ORDER BY groceryId LIMIT 1")
    int deleteGroceryByTableIndex(int rowIndex);



}
