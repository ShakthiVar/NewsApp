package com.srishakthivarshini.newsapp;

public class categoryRVModal {
    private String Category;
    private String CategoryImageUrl;

    public categoryRVModal(String category, String categoryImageUrl) {
        this.Category = category;
        this.CategoryImageUrl = categoryImageUrl;
    }

    public  String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCategoryImageUrl() {
        return CategoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        CategoryImageUrl = categoryImageUrl;
    }
}
