package com.example.bloom3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    BottomNavigationView bottomNavigationView;
    private ArrayList<Plantlist> plantlistArrayList;
    private RecyclerView recyclerview;
    private String[] plantlistHeading;
    private int[] imageResourceID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.myplants);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.plantsnap:
                        startActivity(new Intent(getApplicationContext(), PlantSnap.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myplants:
                        return true;
                    case R.id.myprofile:
                        startActivity(new Intent(getApplicationContext(), MyProfile.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        recyclerview = findViewById(R.id.recyclerView);
        plantlistArrayList = new ArrayList<>();
        setAdapter();

    }

    private void setAdapter() {
        dataInitialize();
        MyAdapter adapter = new MyAdapter(plantlistArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(adapter);
        adapter.addItemClickListener((MyAdapter.ItemClickListener) this);
        adapter.addInfoClickListener((MyAdapter.ItemClickListener) this);

    }

    private void dataInitialize() {
        plantlistArrayList = new ArrayList<>();
        plantlistHeading = new String[]{
                getString(R.string.plant_1),
                getString(R.string.plant_2),
                getString(R.string.plant_3),
                getString(R.string.plant_4),
                getString(R.string.plant_5),
                getString(R.string.plant_6),
        };

        imageResourceID = new int[]{
                R.mipmap.ic_opuntia_foreground,
                R.mipmap.ic_crassula_foreground,
                R.mipmap.ic_zz_foreground,
                R.mipmap.ic_ginseng_foreground,
                R.mipmap.ic_dracena_foreground,
                R.mipmap.ic_sansevieria_foreground

        };

        for(int i =0; i< plantlistHeading.length; i++){

            Plantlist plantlist = new Plantlist(plantlistHeading[i], imageResourceID[i]);
            plantlistArrayList.add(plantlist);
        }
    }

    @Override
    public void onItemClick(int position) {
        System.out.println("Button Item " + position + " clicked");
        Intent intent=new Intent(MainActivity.this, Sensors.class);
        startActivity(intent);
    }

    @Override
    public void onInfoClick(int position) {
        System.out.println("Button Info " + position + " clicked");
        Intent intent;
        switch (position) {
            case 0:
                intent= new Intent(this,Opuntia.class);
                startActivity(intent);
                break;
            case 1:
                intent=new Intent(this,Crassula.class);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(this,ZZ.class);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(this,Ficus.class);
                startActivity(intent);
                break;
            case 4:
                intent=new Intent(this,Dracena.class);
                startActivity(intent);
                break;
            case 5:
                intent=new Intent(this,Sansevieria.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    }
