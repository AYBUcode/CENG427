package com.example.w6_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> cities = new ArrayList<String>();
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareData();

        mListView = (ListView) findViewById(R.id.lv_cities);
        mArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cities);
        mListView.setAdapter(mArrayAdapter);

        // Adding more data to ArrayList
        mArrayAdapter.add("Izmir");
        mArrayAdapter.add("Antalya");
        // Notifies the attached observers that the underlying data has been
        // changed and any View reflecting the data set should refresh itself.
        mArrayAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this,
                        "You have selected " + ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                String selectedItem = mListView.getItemAtPosition(position)
                        .toString();

                mArrayAdapter.remove(selectedItem);
                mArrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, selectedItem + " deleted",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void prepareData() {
        cities.add("Istanbul");
        cities.add("Ankara");
    }
}