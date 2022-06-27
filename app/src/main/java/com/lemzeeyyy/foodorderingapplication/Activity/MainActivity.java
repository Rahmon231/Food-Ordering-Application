package com.lemzeeyyy.foodorderingapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lemzeeyyy.foodorderingapplication.Adapter.CategoryAdapter;
import com.lemzeeyyy.foodorderingapplication.R;
import com.lemzeeyyy.foodorderingapplication.Utils.CategoryDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView categoryRecyclerView;
    private RecyclerView.Adapter categoryAdapter;
    private FirebaseFirestore firestore;
    public static ArrayList<CategoryDomain> categoryList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFoodNames();
    }

    private void loadFoodNames() {
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
                            Log.d("TAG", "onComplete: "+task.getException().getMessage().toString());
                        }
                    }
                });
    }

    private void setupCategoryRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView = findViewById(R.id.category_view1);
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