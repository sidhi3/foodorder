package com.example.ridhi.foodorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

    }
    public void signin(View v){
        Intent in = new Intent(this,Main2Activity.class);
        startActivity(in);

    }
    public void signup(View v)
    {
        Intent up = new Intent(this,Main3Activity.class);
        startActivity(up);
    }
}
