package com.lemzeeyyy.foodorderingapplication.Utils;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    private String title;
    private String picture;
    private String description;
    private Double fee;
    private int star;
    private int time;
    private int calorie;
    private int numberInCart;

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public FoodDomain(String title, String picture, String description, Double fee, int star, int time, int calorie) {
        this.title = title;
        this.picture = picture;
        this.description = description;
        this.fee = fee;
        this.star = star;
        this.time = time;
        this.calorie = calorie;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
