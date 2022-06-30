package com.lemzeeyyy.foodorderingapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lemzeeyyy.foodorderingapplication.Adapter.CategoryAdapter;
import com.lemzeeyyy.foodorderingapplication.Adapter.RecommendedAdapter;
import com.lemzeeyyy.foodorderingapplication.R;
import com.lemzeeyyy.foodorderingapplication.Utils.CategoryDomain;
import com.lemzeeyyy.foodorderingapplication.Utils.FoodDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView,recommendedRecyclerView;
    private RecyclerView.Adapter categoryAdapter, recommendedAdapter;
    private FirebaseFirestore firestore;
    public static ArrayList<CategoryDomain> categoryList;
    public static ArrayList<FoodDomain> recommendedList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCategory();
        loadRecommended();
        bottomNav();
    }
    private void bottomNav() {
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout cartBtn = findViewById(R.id.addToCartBtn_main);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void loadRecommended() {
        recommendedList = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        String docId = firestore.collection("Recommended").document().getId();
        firestore.collection("Recommended").document("RecommendedFood")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                long count = (long) doc.get("count");
                                for (int i = 1; i <= count; i++) {
                                    String recomFoodName = doc.getString("food"+(i)+"_name");
                                    String recomFoodId = doc.getString("food"+(i)+"_id");
                                    int recomFoodCalorie = Math.toIntExact((doc.getLong("food" + (i) + "_calorie")));
                                    String recomFoodDescription = doc.getString("food"+i+"_description");
                                    Double recomFoodPrice = Double.valueOf((doc.getLong("food"+i+"_price")));
                                    int recomFoodNumInCart = Math.toIntExact((doc.getLong("food" + i + "_numincart")));
                                    int recomFoodStar = Math.toIntExact((doc.getLong("food" + i + "_star")));
                                    int recomFoodTime =  (Math.toIntExact(doc.getLong("food" + i + "_time")));
                                    String recomFoodImage = "pop_"+(i-1);
                                    recommendedList.add(new FoodDomain(recomFoodName,recomFoodImage,recomFoodDescription,
                                            recomFoodPrice,recomFoodStar,recomFoodTime,recomFoodCalorie));
                                    setupRecommendedAdapter();

                                }
                            }else {
                                Log.d("TAG", "onComplete: no document found");
                            }
                        }else {
                            Log.d("TAG", "onComplete: "+task.getException().getMessage());
                        }
                    }

                });

    }
    private void setupRecommendedAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        recommendedRecyclerView = findViewById(R.id.recommended_recview);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedAdapter = new RecommendedAdapter(recommendedList);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }

    private void loadCategory() {
        categoryList = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        String docId = firestore.collection("Categories").document().getId();
        firestore.collection("Categories").document("FoodCategories")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                long count = (long) doc.get("count");
                                for (int i = 1; i <= count; i++) {
                                    String foodCatName = doc.getString("food"+(i)+"_name");
                                    String foodCatId = doc.getString("food"+(i)+"_id");
                                    String foodImageUrl = "cat_"+(i-1);

                                    categoryList.add(new CategoryDomain(foodCatName,foodImageUrl,foodCatId));
                                   setupCategoryRecycler();
                                }
                            }else {
                                Log.d("TAG", "onComplete: no document found");
                            }
                        }else {
                            Log.d("TAG", "onComplete: "+task.getException().getMessage());
                        }
                    }
                });
    }

    private void setupCategoryRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView = findViewById(R.id.category_recview);
        categoryRecyclerView.setLayoutManager(layoutManager);
//        categoryList.add(new CategoryDomain("pizza","cat_1","1"));
//        categoryList.add(new CategoryDomain("Burger","cat_2","2"));
//        categoryList.add(new CategoryDomain("Hotdog","cat_3","3"));
//        categoryList.add(new CategoryDomain("Drink","cat_4","4"));
//        categoryList.add(new CategoryDomain("doughnut","cat_5","5"));
        categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }
}