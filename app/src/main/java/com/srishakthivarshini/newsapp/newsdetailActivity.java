package com.srishakthivarshini.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class newsdetailActivity extends AppCompatActivity {

    String title,desc,content,imageUrl,url;
    private TextView titleTV,subdescTV,contentTV;
    private ImageView newsTv;
    private Button readnewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("description");
        content=getIntent().getStringExtra("content");
        imageUrl=getIntent().getStringExtra("imageUrl");
        url=getIntent().getStringExtra("Url");
        titleTV= findViewById(R.id.idTvTitle);
        subdescTV= findViewById(R.id.idSubdescription);
        contentTV= findViewById(R.id.idTvContent);
        newsTv= findViewById(R.id.idTVNews);
        readnewsBtn= findViewById(R.id.idbtnReadNews);
        titleTV.setText(title);
        subdescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageUrl).into(newsTv);
        readnewsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



    }
}