package com.srishakthivarshini.newsapp;

import java.util.ArrayList;



public class NewsModal {
    private int TotalResult;
    private String Status;
    private ArrayList<Articles> articles;

    public NewsModal(int totalResult, String status, ArrayList<Articles> articles) {
        this.TotalResult = totalResult;
        this.Status = status;
        this.articles = articles;
    }
    public int getTotalResult() {
        return TotalResult;
    }

    public void setTotalResult(int totalResult) {
        TotalResult = totalResult;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

}
