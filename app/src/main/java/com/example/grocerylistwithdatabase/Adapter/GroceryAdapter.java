package com.example.grocerylistwithdatabase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocerylistwithdatabase.DAO.GroceriesDAO;
import com.example.grocerylistwithdatabase.Database.Repository;
import com.example.grocerylistwithdatabase.Entity.Groceries;
import com.example.grocerylistwithdatabase.R;
import com.example.grocerylistwithdatabase.UI.GroceryList;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    Repository repository;
    private List<Groceries> mGroceries;
    private final Context context;
    private final LayoutInflater mInflater;

    class GroceryViewHolder extends RecyclerView.ViewHolder {
        private final TextView groceryListRow;

        private GroceryViewHolder(View itemView) {
            super(itemView);
            groceryListRow = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Groceries current = mGroceries.get(position);
//                    repository.delete(current);
//                    mGroceries.remove(position);
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position, mGroceries.size());

                }
            });
        }

    }

    public GroceryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.grocery_list_row, parent, false);
        return new GroceryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if (mGroceries != null) {
            Groceries current = mGroceries.get(position);
            String name = current.getGroceryName();
            holder.groceryListRow.setText(name);
        } else {
            holder.groceryListRow.setText("No grocery name");
        }
    }

    public void setGroceries(List<Groceries> groceries) {
        mGroceries = groceries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGroceries.size();
    }
}
