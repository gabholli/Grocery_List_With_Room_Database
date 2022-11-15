package com.example.grocerylistwithdatabase.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grocerylistwithdatabase.Adapter.GroceryAdapter;
import com.example.grocerylistwithdatabase.Database.Repository;
import com.example.grocerylistwithdatabase.Entity.Groceries;
import com.example.grocerylistwithdatabase.R;

// Main screen
public class GroceryList extends AppCompatActivity {

    Repository repository;
    EditText addItem;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);
        addItem = findViewById(R.id.addEditText);
        addButton = findViewById(R.id.addButton);
        repository = new Repository(getApplication());
        java.util.List<Groceries> groceriesList = repository.getAllGroceries();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final GroceryAdapter adapter = new GroceryAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setGroceries(groceriesList);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForEmptySpaces(addItem.getText().toString())) {
                    Toast.makeText(GroceryList.this, "Please make proper entry",
                            Toast.LENGTH_LONG).show();
                } else {
                    Groceries groceries;
                    groceries = new Groceries(addItem.getText().toString());
                    groceriesList.add(groceries);
                    repository.insert(groceries);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GroceryList.this);
                builder.setTitle("Delete Item?");
                builder.setMessage("Are You Sure You Want To Delete This Item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = viewHolder.getAdapterPosition();
                        groceriesList.remove(position);
                        repository.deleteGroceryByTableIndex(position);
                        adapter.notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    }
                });

                builder.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public boolean checkForEmptySpaces(String value) {
        return value.length() > 0 && value.trim().matches("") || value.isEmpty();
    }
    public void addButton(View view) {
//        Groceries groceries;
//        groceries = new Groceries(addItem.getText().toString());
//        repository.insert(groceries);
    }

    public void deleteButton(View view) {
    }
}