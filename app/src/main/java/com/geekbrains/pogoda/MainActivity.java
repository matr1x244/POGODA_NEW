package com.geekbrains.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "MainActivity"; // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.

    //startActivity(new Intent(this, ActivityWeather.class)); - это не понял с урока

    String[] cities = {"Москва", "Йошкар-Ола", "Вологда", "Волгоград", "Саратов", "Воронеж"};
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // запуск главного экрана
        Button buttonSearch = (Button)findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener((View.OnClickListener) this);

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
        CheckBox pressure = (CheckBox) findViewById(R.id.pressure);

        // пока что отоброжать текст -- потом удалить - пока для тестов
        String selectedItems = "";
        if (speed_weather.isChecked())
            selectedItems += speed_weather.getText() + " / ";
        if (pressure.isChecked())
            selectedItems += pressure.getText();

        // пока что отоброжать текст -- потом удалить - пока для тестов
        TextView selection = (TextView) findViewById(R.id.selection);
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



}