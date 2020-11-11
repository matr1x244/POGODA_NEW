package com.geekbrains.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class CoatActivityFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coat_fragment);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        if(savedInstanceState == null){
            CoatFragment details = new CoatFragment();
            details.setArguments(getIntent().getExtras());
            // добавить фрагмент
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,details).commit();
        }
    }
}