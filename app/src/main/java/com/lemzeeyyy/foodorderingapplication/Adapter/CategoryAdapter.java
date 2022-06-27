package com.lemzeeyyy.foodorderingapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lemzeeyyy.foodorderingapplication.R;
import com.lemzeeyyy.foodorderingapplication.Utils.CategoryDomain;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryDomain> categoryDomainArrayList;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomainArrayList) {
        this.categoryDomainArrayList = categoryDomainArrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_catogory,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomainArrayList.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:
                picUrl = "cat_1";
                break;
            case 1:
                picUrl = "cat_2";
                break;
            case 2:
                picUrl = "cat_3";
                break;
            case 3:
                picUrl = "cat_4";
                break;
            case 4:
                picUrl = "cat_5";
                break;

        }
        int drawableResourseId = holder.itemView.getContext().getResources()
                .getIdentifier(picUrl,"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourseId)
                .into(holder.categoryImage);


    }

    @Override
    public int getItemCount() {
        return categoryDomainArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryImage;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryImage = itemView.findViewById(R.id.category_pic);
            mainLayout = itemView.findViewById(R.id.mainLayoutCat);
        }
    }
}
