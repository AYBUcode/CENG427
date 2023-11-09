package com.example.w6_3;

import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] cities = { "Istanbul", "Ankara", "Izmir", "Antalya",
            "Konya", "Izmit", "Bursa", "Kayseri", "Malatya", "Trabzon",
            "Antakya", "Erzurum", "Van" };
    //String[] cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DO NOT FORGET TO LOAD YOUR XML
        setContentView(R.layout.activity_main);

        ListView lstView = getListView();
        //lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        //lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstView.setTextFilterEnabled(true);

        //cities = getResources().getStringArray(R.array.cities_array);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, cities));
    }

    /*public void onListItemClick(
            ListView parent, View v, int position, long id)
    {
        Toast.makeText(this,
                "You have selected " + cities[position],
                Toast.LENGTH_SHORT).show();
    }*/

    public void onClick(View view) {
        ListView lstView = getListView();
        String itemsSelected = "Selected items: \n";
        for (int i=0; i<lstView.getCount(); i++) {
            if (lstView.isItemChecked(i)) {
                itemsSelected += lstView.getItemAtPosition(i) + "\n";
            }
        }
        //customized toast by a customized text view
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 100,0);

        TextView tv = new TextView(MainActivity.this);
        tv.setBackgroundColor(Color.BLACK);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20);

        Typeface t = Typeface.create("serif", Typeface.BOLD_ITALIC);
        tv.setTypeface(t);
        tv.setPadding(10,10,10,10);
        tv.setText(itemsSelected);

        toast.setView(tv);
        toast.show();
    }
}