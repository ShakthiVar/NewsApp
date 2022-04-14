package com.srishakthivarshini.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class news extends AppCompatActivity{

    String title,desc,content,imageURL,Url;
    protected void OnCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_newsdetail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("imageURL");
         Url=getIntent().getStringExtra("url");


            }

        }
