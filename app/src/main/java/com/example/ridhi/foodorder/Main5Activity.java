package com.example.ridhi.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ridhi.foodorder.Interface.ItemClickListner;
import com.example.ridhi.foodorder.viewholder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.ridhi.foodorder.R.layout.food_item;

public class Main5Activity extends AppCompatActivity {
    FirebaseDatabase db ;
    DatabaseReference foodlist;

    RecyclerView rv2;
    RecyclerView.LayoutManager layoutManager;
    String categoryid="";
    FirebaseRecyclerAdapter<Food,FoodViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        db = FirebaseDatabase.getInstance();
        foodlist = db.getReference("Food");

        rv2 = findViewById(R.id.rv2);
        rv2.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv2.setLayoutManager(layoutManager);

        if (getIntent() != null)
            categoryid = getIntent().getStringExtra("categoryid");
        if (!categoryid.isEmpty() && categoryid != null) {
            LoadList(categoryid);


        }
    }

    private void LoadList(String categoryid) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.food_item,FoodViewHolder.class,
                foodlist.orderByChild("menuid").equalTo(categoryid))
        {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, final int position) {
                viewHolder.food_text.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);

               final Food local=model;
               viewHolder.setItemClickListner(new ItemClickListner() {
                   @Override
                   public void onClick(View v, int i, boolean isLongClick) {
                       Intent in = new Intent(Main5Activity.this,Main6Activity.class);
                       in.putExtra("foodid",adapter.getRef(position).getKey());
                       startActivity(in);
                   }
               });
            }
        };
        Log.d("TAG",""+adapter.getItemCount());
        rv2.setAdapter(adapter);
    }
}
