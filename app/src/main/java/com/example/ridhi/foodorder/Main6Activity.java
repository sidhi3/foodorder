package com.example.ridhi.foodorder;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ridhi.foodorder.Database.Database;
import com.example.ridhi.foodorder.model.order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Main6Activity extends AppCompatActivity {

    TextView foodname,foodprice;
    ImageView foodimage;
    CollapsingToolbarLayout collaps;
    FloatingActionButton btncart;
    ElegantNumberButton numberButton;

    String foodid="";

    FirebaseDatabase db ;
    DatabaseReference foods;
    Food currentfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        db = FirebaseDatabase.getInstance();
        foods = db.getReference("Food");

        numberButton=findViewById(R.id.number_button);
        btncart=findViewById(R.id.btncart);

        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new Database(getBaseContext()).addtoCart(new order(
                      foodid,
                      currentfood.getName(),
                      numberButton.getNumber(),
                      currentfood.getPrice()
              ));
                Toast.makeText(Main6Activity.this,"sdded to cart",Toast.LENGTH_SHORT).show();
            }
        });

        foodname=findViewById(R.id.food_name);
        foodimage=findViewById(R.id.img_food);
        foodprice=findViewById(R.id.food_price);

        collaps=findViewById(R.id.collaps);
        collaps.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);
        collaps.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);

        if (getIntent() != null)
            foodid = getIntent().getStringExtra("foodid");
        if (!foodid.isEmpty()) {
            getfood(foodid);
        }

        }

    private void getfood(String foodid) {

        foods.child(foodid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentfood = dataSnapshot.getValue(Food.class);
                Picasso.with(getBaseContext()).load(currentfood.getImage())
                        .into(foodimage);
                collaps.setTitle(currentfood.getName());
                foodprice.setText(currentfood.getPrice());
                foodname.setText(currentfood.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
