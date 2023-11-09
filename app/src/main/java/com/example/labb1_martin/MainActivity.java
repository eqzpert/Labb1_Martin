package com.example.labb1_martin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherXmlParser parser = new WeatherXmlParser();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        WeatherXmlParser weatherXmlParser = new WeatherXmlParser();

        executor.execute(() -> {
            try {
                //List entries;
                //entries = parser.loadXmlFromNetwork("https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=64.62;lon=21.20");
                List<WeatherXmlParser.Entry> entries = weatherXmlParser.loadXmlFromNetwork("https://api.met.no/weatherapi/locationforecast/2.0/classic?lat=64.62;lon=21.20");

                handler.post(() -> {
                    for (WeatherXmlParser.Entry entry: entries) {
                        System.out.println("Temperature: " + entry.temperature);
                        System.out.println("Cloudiness: " + entry.cloudiness);
                        System.out.println("Humidity: " + entry.humidity);
                    }

                });
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
                handler.post(() -> {
                    System.out.println("gick fel");

                });
            }
        });


    }
}


//Hej Emil & Emil

//hahahahahaha


//Test Vestin
//Test Alfred
//Test Filip

