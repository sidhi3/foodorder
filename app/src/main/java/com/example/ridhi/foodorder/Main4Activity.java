package com.example.ridhi.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ridhi.foodorder.Interface.ItemClickListner;
import com.example.ridhi.foodorder.comman.comman;
import com.example.ridhi.foodorder.viewholder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main4Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase db ;
    DatabaseReference category;
    TextView t;
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<category, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_main4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        db=FirebaseDatabase.getInstance();
        category=db.getReference("category");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent cartintent = new Intent(Main4Activity.this,Main7Activity.class);
               startActivity(cartintent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);
        t=headerview.findViewById(R.id.tv);
        t.setText(comman.cu.getName());
        
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        loadMenu();

    }

    private void loadMenu() {
         adapter= new FirebaseRecyclerAdapter<category, MenuViewHolder>(category.class,R.layout.menu_item,MenuViewHolder.class,category) {

            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, category model, final int position) {
                viewHolder.tv1.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.im1);
                final category clickitem = model;
                viewHolder.setItemClickListner(new ItemClickListner() {
                    @Override
                    public void onClick(View v, int adapterPosition, boolean isLongClick) {
                        Intent foodlist =new Intent(Main4Activity.this,Main5Activity.class);
                        foodlist.putExtra("categoryid",adapter.getRef(position).getKey());
                        startActivity(foodlist);
                    }
                });
            }
        };
        rv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu) {
            // Handle the camera action
        } else if (id == R.id.cart) {

        } else if (id == R.id.logout) {

        } else if (id == R.id.orders) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
