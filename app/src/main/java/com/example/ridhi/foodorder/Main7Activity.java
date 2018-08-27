package com.example.ridhi.foodorder;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ridhi.foodorder.Database.Database;
import com.example.ridhi.foodorder.comman.comman;
import com.example.ridhi.foodorder.model.Request;
import com.example.ridhi.foodorder.model.order;
import com.example.ridhi.foodorder.viewholder.cartadapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Main7Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference requests;

    TextView txttotalprice;
    FButton place;

    List<order>  cart = new ArrayList<>();
    cartadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        db = FirebaseDatabase.getInstance();
        requests=db.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listcart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txttotalprice = (TextView)findViewById(R.id.total);
        place = (FButton)findViewById(R.id.place);

        place.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void  onClick(View view){
                //create new request
                showAlertDialog();
            }
        });

        loadlistfood();

    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Main7Activity.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your address: ");

        final EditText edtAddress = new EditText(Main7Activity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //add edit text to alert dialog
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //create new request
                Request request = new Request(
                        comman.cu.getPhone(),
                        comman.cu.getName(),
                        edtAddress.getText().toString(),
                        txttotalprice.getText().toString(),
                        cart

                );
                //submit to firebase
                requests.child(String .valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //delete cart
                new  Database(getBaseContext()).cleancart();
                Toast.makeText(Main7Activity.this, "Thank You, Order Place", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new  DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadlistfood() {
        cart = new Database(this).getcarts();
        adapter = new cartadapter(cart,this);
        recyclerView.setAdapter(adapter);

        //calculate full total price
        int total  = 0;
        for (order o:cart) {
        //   if ((null != o.getPrice()) && (null != o.getQuantity()))
                total += (Integer.parseInt(o.getPrice())) * (Integer.parseInt(o.getQuantity()));
        }
            Locale locale = new Locale("en", "US");
            NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txttotalprice.setText(fmt.format(total));

    }

}