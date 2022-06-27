package com.lemzeeyyy.foodorderingapplication.Utils;

public class CategoryDomain {
    private String title;
    private String picture;
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDomain(String title, String picture, String categoryId) {
        this.title = title;
        this.picture = picture;
        this.categoryId = categoryId;
    }

    public CategoryDomain() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
