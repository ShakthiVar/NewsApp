package com.srishakthivarshini.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryOnclickListener {

    //2e18362f302c4afa8f589d80c0fae2cf-->api key
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<categoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private newsRVAdapter NewsRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        NewsRVAdapter = new newsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,this,this::OncategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(NewsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getcategories();
        getnews("All");
        NewsRVAdapter.notifyDataSetChanged();
    }
    private void getcategories(){
        categoryRVModalArrayList.add(new categoryRVModal("TECHNOLOGY","https://unsplash.com/photos/XJXWbfSo2f0"));
        categoryRVModalArrayList.add(new categoryRVModal("SCIENCE","https://www.istockphoto.com/photo/female-chemist-at-work-in-laboratory-gm637785818-113951865?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fscience&utm_term=science%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("ENTERTAINMENT","https://www.istockphoto.com/photo/the-best-fans-a-band-could-want-gm502131959-43978748?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fentertainment&utm_term=entertainment%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("SPORTS","https://www.istockphoto.com/photo/various-sport-equipments-on-grass-gm949190736-259119233?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fsports&utm_term=sports%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("GENERAL","https://www.istockphoto.com/photo/common-mistakes-motivational-words-quotes-concept-gm1145889040-308573924?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fgeneral&utm_term=general%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("POLITICS","https://www.istockphoto.com/photo/us-capitol-building-washington-dc-gm1310677731-400003227?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fpolitics&utm_term=politics%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("HEALTH","https://www.istockphoto.com/photo/healthy-lifestyle-on-ketogenic-diet-eating-clean-keto-food-good-health-dietary-in-gm1311936090-400883663?utm_source=unsplash&utm_medium=affiliate&utm_campaign=srp_photos_top&utm_content=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fhealth&utm_term=health%3A%3A%3A"));
        categoryRVModalArrayList.add(new categoryRVModal("BUSINESS","https://unsplash.com/photos/5fNmWej4tAA"));
        categoryRVAdapter.notifyDataSetChanged();
    }
    private void getnews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String CategoryURL="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=2e18362f302c4afa8f589d80c0fae2cf";
        String Url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=2e18362f302c4afa8f589d80c0fae2cf";
        String base_url="https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("ALL")){
            call= retrofitAPI.getAllNews(Url);
        }else{
            call= retrofitAPI.getNewsByCategory(CategoryURL);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                newsRV.setVisibility(View.VISIBLE);
                ArrayList<Articles>articles = newsModal.getArticles();
                NewsRVAdapter.setArticlesArrayList(Articles);
                Log.d("MainActivity","Hello"+NewsRVAdapter.articlesArrayList.size());
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get News", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void OncategoryClick(int position) {
        String category = categoryRVModalArrayList.get(position).getCategory();
        getnews(category);

    }

}