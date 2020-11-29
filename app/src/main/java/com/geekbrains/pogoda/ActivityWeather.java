package com.geekbrains.pogoda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geekbrains.pogoda.model.entity.WeatherRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;


public class ActivityWeather extends AppCompatActivity {

    private static final String TAG = ActivityWeather.class.getSimpleName(); // TAG для логов
    private final static boolean DEBAG = true; //включение DEBAGa для логов.

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?id=466806&appid=";
    //private static final String WEATHER_API_KEY = "48782d1c69f3bfe83f6c53043d67accb";


    //---------
    private TextView city_name;
    //---------

    private EditText city;
    private EditText temperature;
    private EditText pressure;
    private EditText humidity;
    private EditText windSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        init();

        //-----
        city_name = (TextView) findViewById(R.id.textView_city);
        //-----

        //-----
        String cityName = getIntent().getStringExtra("Город..");
        city_name.setText(city_name.getText().toString() + " " + cityName);
        //-----

    }

    private void init() {
        city = findViewById(R.id.textCity);
        temperature = findViewById(R.id.textTemprature);
        pressure = findViewById(R.id.textPressure);
        humidity = findViewById(R.id.textHumidity);
        windSpeed = findViewById(R.id.textWindspeed);
        Button buttonreload = findViewById(R.id.buttonReload);
        buttonreload.setOnClickListener(clickListener);
    }

    final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            try {
                final URL uri = new URL(WEATHER_URL + BuildConfig.WEATHER_API_KEY);
                final Handler handler = new Handler(); // Запоминаем основной поток
                new Thread(new Runnable() {
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(100000); // установка таймаута
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                            String result = getLines(in);
                            // преобразование данных запроса в модель
                            Gson gson = new Gson();
                            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                            // Возвращаемся к основному потоку
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    displayWeather(weatherRequest);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        } finally {
                            if (null != urlConnection) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Fail URI", e);
                e.printStackTrace();
            }
        }

        private String getLines(BufferedReader in) {
            return in.lines().collect(Collectors.joining("\n"));
        }

        @SuppressLint("DefaultLocale")
        private void displayWeather(WeatherRequest weatherRequest) {
            city.setText(weatherRequest.getName());
            temperature.setText(String.format("%d", weatherRequest.getMain().getTemperature()));
            pressure.setText(String.format("%d", weatherRequest.getMain().getPressure()));
            humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
            windSpeed.setText(String.format("%d", weatherRequest.getWind().getSpeed()));
        }
    };





        /*@Override
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
         */


        public void btnWorld(View v) {
            if (v.getId() == R.id.buttonWorld) {
                Uri addres = Uri.parse("https://pogoda.turtella.ru/weathermap");
                Intent Web = new Intent(Intent.ACTION_VIEW, addres);
                Toast.makeText(this, "В браузер..", Toast.LENGTH_SHORT).show();
                if (DEBAG) {
                    Log.d(TAG, "buttonWorld");
                    startActivity(Web);
                }
            }
        }
    }







