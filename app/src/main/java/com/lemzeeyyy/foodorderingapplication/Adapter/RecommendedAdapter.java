package com.lemzeeyyy.foodorderingapplication.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lemzeeyyy.foodorderingapplication.Activity.ShowDetailsActivity;
import com.lemzeeyyy.foodorderingapplication.Utils.FoodDomain;
import com.lemzeeyyy.foodorderingapplication.R;
import com.lemzeeyyy.foodorderingapplication.Utils.FoodDomain;

import java.util.ArrayList;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    ArrayList<FoodDomain> recFoodDomains;

    public RecommendedAdapter(ArrayList<FoodDomain> recFoodDomains) {
        this.recFoodDomains = recFoodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_recommended, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(recFoodDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(recFoodDomains.get(position).getFee()));
        String picUrl = "";
        switch (position){
            case 0:
                picUrl = "pop_1";
                break;
            case 1:
                picUrl = "pop_2";
                break;
            case 2:
                picUrl = "pop_3";
                break;
        }
        int drawableResourseId = holder.itemView.getContext().getResources()
                .getIdentifier(picUrl,"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourseId)
                .into(holder.pic);
        holder.addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
            intent.putExtra("object",recFoodDomains.get(position));
            holder.itemView.getContext().startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return recFoodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee;
        ImageView pic,addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rec_title);
            fee = itemView.findViewById(R.id.rec_title2);
            pic = itemView.findViewById(R.id.rec_pic);
            addBtn = itemView.findViewById(R.id.imageView10);


        }
    }
}
