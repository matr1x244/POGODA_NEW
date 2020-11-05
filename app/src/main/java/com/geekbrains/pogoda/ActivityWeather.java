package com.geekbrains.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityWeather extends AppCompatActivity  implements View.OnClickListener {

    private static final String TAG = "ActivityWeather"; // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.

    //---------
    private TextView city_name;
    //---------


    TextView textView_speed_w;
    TextView textView_pressure_w;
    TextView textView_degrees_w;

    public int speed_w = 5;
    public int pressure_w = 0;
    public int degrees_w = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        city_name = (TextView) findViewById(R.id.textView_city);
        Button back_activity = (Button) findViewById(R.id.buttonBack);
        back_activity.setOnClickListener((View.OnClickListener) this);

        //-----
        String cityName = getIntent().getStringExtra("Город..");
        city_name.setText(city_name.getText().toString() + " " + cityName);
        //-----

        textView_speed_w = (TextView) findViewById(R.id.textView_speed_w);
        textView_speed_w.setText("Скорость ветра: " + String.valueOf(speed_w));

        textView_pressure_w = (TextView) findViewById(R.id.textView_pressure_w);
        textView_pressure_w.setText("Давление: " + String.valueOf(pressure_w));

        textView_degrees_w = (TextView) findViewById(R.id.textView_degrees_w);
        textView_degrees_w.setText("Градусы: " + String.valueOf(degrees_w));
    }

    // @Override
    // public void onClick(View view) {
    //  Intent Back = new Intent(this, MainActivity.class);
    //   startActivity(Back);
    // }

    //Кнопка назад
    //   public void Back (View view) {
    //   Toast.makeText(this, "Назад", Toast.LENGTH_SHORT).show();
    //    if (DEBAG) {
    //        Log.d(TAG, "buttonBack");
    //    }
    // }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonBack) {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
            Toast.makeText(this, "Назад", Toast.LENGTH_SHORT).show();
            if (DEBAG) {
                Log.d(TAG, "buttonBack");
            }
        }
    }
}


