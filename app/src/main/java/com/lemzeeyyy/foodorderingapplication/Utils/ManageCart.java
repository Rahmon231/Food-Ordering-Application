package com.lemzeeyyy.foodorderingapplication.Utils;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManageCart {
    private Context context;
    private FirebaseFirestore firestore;

    public ManageCart(Context context) {
        this.context = context;
        firestore = FirebaseFirestore.getInstance();
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> foodList = new ArrayList<>();
        boolean existAlready = false;
        int n = 0 ;
        for (int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if(existAlready){
            foodList.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            foodList.add(item);
        }
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }
}
