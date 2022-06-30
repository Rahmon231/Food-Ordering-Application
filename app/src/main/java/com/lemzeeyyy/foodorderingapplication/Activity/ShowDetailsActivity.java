package com.lemzeeyyy.foodorderingapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lemzeeyyy.foodorderingapplication.R;
import com.lemzeeyyy.foodorderingapplication.Utils.FoodDomain;

import java.util.ArrayList;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView addToCartBtn,titleTxt, descriptionTxt, numberOfOrderTxt,feeTxt;
    private TextView totalPrice, starTxt,calorieTxt,timeTxt;
    private ImageView plusBtn,minusBtn,foodPic;
    private int numberOrder = 1 ;
    FoodDomain object;
    ArrayList<FoodDomain> recFood = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        initView();
       getBundle();

    }
    public void getBundle(){
        object = (FoodDomain)getIntent().getSerializableExtra("object");

        int drawableResId = this.getResources().getIdentifier(object.getPicture(),
                "drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResId)
                .into(foodPic);
        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOfOrderTxt.setText(String.valueOf(numberOrder));
        calorieTxt.setText(object.getCalorie()+" calories");
        starTxt.setText(object.getStar()+"");
        totalPrice.setText("$"+Math.round(numberOrder*object.getFee()));
        timeTxt.setText(object.getTime()+" minutes");

        plusBtn.setOnClickListener(view -> {
            numberOrder = numberOrder + 1;
            numberOfOrderTxt.setText(String.valueOf(numberOrder));
            totalPrice.setText("$"+Math.round(numberOrder*object.getFee()));
        });

        minusBtn.setOnClickListener(view -> {
            if(numberOrder>1)
                numberOrder = numberOrder - 1;
            numberOfOrderTxt.setText(String.valueOf(numberOrder));
            totalPrice.setText("$"+Math.round(numberOrder*object.getFee()));
        });

        addToCartBtn.setOnClickListener(view -> object.setNumberInCart(numberOrder));

    }
    private void initView() {
        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOfOrderTxt = findViewById(R.id.numberitemTxt);
        feeTxt = findViewById(R.id.priceTxt);
        plusBtn = findViewById(R.id.plus_card_btn_details);
        minusBtn = findViewById(R.id.minus_card_btn_details);
        foodPic = findViewById(R.id.foodpic_showdetails);
        totalPrice = findViewById(R.id.total_price_txt_details);
        starTxt = findViewById(R.id.star_txt);
        timeTxt = findViewById(R.id.time_txt);
        calorieTxt = findViewById(R.id.calory_txt);

    }
}