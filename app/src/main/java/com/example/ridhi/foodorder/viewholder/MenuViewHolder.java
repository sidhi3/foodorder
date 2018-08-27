package com.example.ridhi.foodorder.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ridhi.foodorder.Interface.ItemClickListner;
import com.example.ridhi.foodorder.R;

/**
 * Created by Ridhi & Sidhi on 1/21/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tv1;
    public ImageView im1;
    ItemClickListner itemClickListner;

    public MenuViewHolder(View itemView) {
        super(itemView);

        tv1=itemView.findViewById(R.id.menu_text);
        im1=itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);
    }
}
