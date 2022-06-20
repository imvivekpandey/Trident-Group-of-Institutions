package com.example.tridentgroupofinstitutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SemesterResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_results);
        getSupportActionBar().hide();
    }
}