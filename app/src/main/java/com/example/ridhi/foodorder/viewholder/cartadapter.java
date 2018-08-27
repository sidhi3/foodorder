package com.example.ridhi.foodorder.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.ridhi.foodorder.Interface.ItemClickListner;
import com.example.ridhi.foodorder.R;
import com.example.ridhi.foodorder.model.order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ridhi & Sidhi on 1/28/2018.
 */

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView itemname , price;

    public void setItemname(TextView itemname) {
        this.itemname = itemname;
    }

    public ImageView itemimage;

    private ItemClickListner itemClickListner;

    public CartViewHolder(View itemView) {
        super(itemView);
        itemname=itemView.findViewById(R.id.itemname);
        price=itemView.findViewById(R.id.itemprice);
        itemimage=itemView.findViewById(R.id.itemimage);
    }

    @Override
    public void onClick(View v) {

    }
}

public class cartadapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<order> listData = new ArrayList<>();
    private Context context;

    public cartadapter(List<order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart, viewGroup, false);
        return new CartViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(CartViewHolder holder, int i) {
        TextDrawable drawable= TextDrawable.builder()
                .buildRound(""+listData.get(i).getQuantity(), Color.RED);
        holder.itemimage.setImageDrawable(drawable);
        int cost = 0;
        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
       if((null!=listData.get(i).getPrice()) &&(null!=listData.get(i).getQuantity()) ) {
            cost = (Integer.parseInt(listData.get(i).getPrice())) * (Integer.parseInt(listData.get(i).getQuantity()));
        }
            holder.price.setText(fmt.format(cost));
            holder.itemname.setText(listData.get(i).getProductname());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
