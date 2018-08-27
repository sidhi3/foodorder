package com.example.ridhi.foodorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ridhi.foodorder.comman.comman;
import com.example.ridhi.foodorder.model.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    Button b;
    EditText e1,e2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        e1=findViewById(R.id.ed1);
        e2=findViewById(R.id.ed2);
        b=findViewById(R.id.btn3);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference table = db.getReference("user");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog d = new ProgressDialog(Main2Activity.this);
                d.setMessage("Waiting.....");
                d.show();

                table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(e1.getText().toString()).exists()) {
                            d.dismiss();
                            user u = dataSnapshot.child(e1.getText().toString()).getValue(user.class);
                            u.setPhone(e1.getText().toString()); //set phone
                            if (u.getPass().equals(e2.getText().toString())) {
                                {
                                    Intent home = new Intent(Main2Activity.this,Main4Activity.class);
                                    comman.cu=u;
                                    startActivity(home);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(Main2Activity.this, "Signin is failed ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            d.dismiss();
                            Toast.makeText(Main2Activity.this, "user does not exist ", Toast.LENGTH_SHORT).show();
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
