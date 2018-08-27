package com.example.ridhi.foodorder;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ridhi.foodorder.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
    EditText e4,e5,e3;
    Button bn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e3=findViewById(R.id.ed3);
        e4=findViewById(R.id.ed4);
        e5=findViewById(R.id.ed5);
        bn=findViewById(R.id.btn4);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference table = db.getReference("user");

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(e3.getText().toString()).exists()){

                            Toast.makeText(Main3Activity.this,"phone number already exist",Toast.LENGTH_SHORT).show();

                        }
                        else {

                            user u = new user(e4.getText().toString(),e5.getText().toString());
                            table.child(e3.getText().toString()).setValue(u);
                            Toast.makeText(Main3Activity.this,"sign up successfully",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
