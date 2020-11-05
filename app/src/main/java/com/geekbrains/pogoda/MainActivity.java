package com.geekbrains.pogoda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "MainActivity"; // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.
    private TextView selection;

    //---------
    private EditText cityName;
    private Button buttonSearch;
//---------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // запуск главного экрана

        cityName = (EditText) findViewById(R.id.cityName);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener((View.OnClickListener) this);

        selection = (TextView) findViewById(R.id.selection);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                String item = (String) parent.getItemAtPosition(position);
                selection.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    public void onCheckboxClicked(View view) {

        // Получаем флажки
        CheckBox speed_weather = (CheckBox) findViewById(R.id.speed_weather);
        Log.d(TAG, "speed_weather");
        CheckBox pressure = (CheckBox) findViewById(R.id.pressure);
        Log.d(TAG, "pressure_weather");

        // пока что отоброжать текст -- потом удалить - пока для тестов
        String selectedItems = "";
        if (speed_weather.isChecked())
            selectedItems += speed_weather.getText() + " / ";
        if (pressure.isChecked())
            selectedItems += pressure.getText();

        // пока что отоброжать текст -- потом удалить - пока для тестов
        TextView selection = (TextView) findViewById(R.id.selection);
        Log.d(TAG, "selection");
        selection.setText(selectedItems);
        // пока что отоброжать текст -- потом удалить - пока для тестов
    }

    //-----
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonSearch) {
            Toast.makeText(this, "Поиск..", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "buttonSearchSend");
            Intent search = new Intent(this, ActivityWeather.class);
            search.putExtra("Город..", cityName.getText().toString());
            startActivity(search);
        }
    }

    //-----

        // С Урока №4
        // запуск страницы в браузере
        //Uri addres = Uri.parse("https://гибдд12.рф");
        //Intent Web = new Intent(Intent.ACTION_VIEW, addres);
        //startActivity(Web);
        // запуск страницы в браузере
        // С Урока №4
    //}


    // Log.d (TAG)

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    // Log.d (TAG)

}