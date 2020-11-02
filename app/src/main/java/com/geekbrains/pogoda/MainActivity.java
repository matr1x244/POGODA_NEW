package com.geekbrains.pogoda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "MainActivity"; // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.

    String[] cities = {"Москва", "Йошкар-Ола", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // запуск главного экрана
        Button buttonSearch = (Button)findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener((View.OnClickListener) this);

        //Log.i(TAG, "OnCreate");

        selection = (TextView) findViewById(R.id.selection);

        Spinner spinner = (Spinner) findViewById(R.id.cities);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

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
        spinner.setOnItemSelectedListener(itemSelectedListener);
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
        //
    }

//// УДАЛИТЬ
    //Кнопка поиска
    /* c урока удалить
    private View.OnClickListener buttonSearch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Поиск", Toast.LENGTH_SHORT).show();
            Log.d(TAG,"buttonSearch");
        }
    };
     */
//// УДАЛИТЬ

    @Override
    public void onClick(View view) {
        Intent Search;
        Search = new Intent(this, ActivityWeather.class);
        startActivity(Search);
    }

    //Кнопка поиска
    public void buttonSearch(View view) {
        Toast.makeText(MainActivity.this, "Поиск", Toast.LENGTH_SHORT).show();
        if (DEBAG) {
            Log.d(TAG, "buttonSearch");
        }
    }

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