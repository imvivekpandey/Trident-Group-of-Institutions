package com.example.tridentgroupofinstitutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CareerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_details);
        getSupportActionBar().hide();
    }
}