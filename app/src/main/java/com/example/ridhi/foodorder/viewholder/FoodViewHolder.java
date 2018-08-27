package com.example.ridhi.foodorder.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ridhi.foodorder.Interface.ItemClickListner;
import com.example.ridhi.foodorder.R;

/**
 * Created by Ridhi & Sidhi on 1/25/2018.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView food_text;
    public ImageView food_image;

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    ItemClickListner itemClickListner;



    public FoodViewHolder(View itemView) {
        super(itemView);

        food_text=itemView.findViewById(R.id.food_text);
        food_image=itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
