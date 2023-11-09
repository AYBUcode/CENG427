package com.example.w4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    String[] cities = {
            "Ankara",
            "Antalya",
            "Antakya",
            "izmir",
            "izmit",
            "iznik",
            "Konya",
            "Konakli",
            "Kirklareli",
            "Kirsehir",
            "Kirikkale"
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, cities);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.txtcities);
        textView.setThreshold(3);
        textView.setAdapter(adapter);
    }
}