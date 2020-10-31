package com.geekbrains.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityWeather extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "ActivityWeather"; // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Button buttonBack = (Button)findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view) {
        Intent Back;
        Back = new Intent(this, MainActivity.class);
        startActivity(Back);
    }

    //Кнопка назад
        public void buttonBack(View view) {
        Toast.makeText(ActivityWeather.this, "Назад", Toast.LENGTH_SHORT).show();
        if (DEBAG) {
            Log.d(TAG, "buttonBack");
        }
    }
}

