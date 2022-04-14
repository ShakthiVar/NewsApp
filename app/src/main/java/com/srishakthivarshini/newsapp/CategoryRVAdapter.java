package com.srishakthivarshini.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private final ArrayList<categoryRVModal>CategoryRVModals;
    private final Context context;
    private final CategoryOnclickListener categoryOnclickListener;

    public CategoryRVAdapter(ArrayList<categoryRVModal> CategoryRVModals, Context context, CategoryOnclickListener categoryOnclickListener) {
        this.CategoryRVModals = CategoryRVModals;
        this.context = context;
        this.categoryOnclickListener = categoryOnclickListener;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_items,parent,false);
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        categoryRVModal categoryRVModals = CategoryRVModals.get(position);
        holder.categoryTv.setText(categoryRVModals.getCategory());
        Picasso.get().load(categoryRVModals.getCategoryImageUrl()).into(holder.categoryIv);
        holder.itemView.setOnClickListener(v -> categoryOnclickListener.OncategoryClick(position));
    }

    @Override
    public int getItemCount() {
        return CategoryRVModals.size();
    }
    public interface CategoryOnclickListener{
        void OncategoryClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryTv;
        private final ImageView categoryIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTv = itemView.findViewById(R.id.idTVCategory);
            categoryIv = itemView.findViewById(R.id.idIVCategory);
        }
    }
}