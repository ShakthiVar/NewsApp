package com.srishakthivarshini.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newsRVAdapter extends RecyclerView.Adapter<newsRVAdapter.ViewHolder>{
    public ArrayList<Articles>articlesArrayList;
    private Context context;

    public void setArticlesArrayList(ArrayList<Articles> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
        notifyDataSetChanged();
    }

    public newsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public newsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new newsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsTV);
        holder.itemView.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,newsdetailActivity.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("description",articles.getDescription());
                i.putExtra("content",articles.getContent());
                i.putExtra("imageUrl",articles.getUrlToImage());
                i.putExtra("Url",articles.getUrl());
                context.startActivity(i);

            }

            });
        }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView titleTV;
        private TextView subTitleTV;
       private ImageView newsTV;
       public ViewHolder(@NonNull View itemView){
           super(itemView);
           titleTV = (TextView) itemView.findViewById(R.id.idTVNewsHeading);
           subTitleTV = (TextView) itemView.findViewById(R.id.idTVSubTitle);
           newsTV = (ImageView) itemView.findViewById(R.id.idTVNews);

       }
    }
}
