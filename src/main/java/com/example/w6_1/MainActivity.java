package com.example.w6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    String[] cities = { "Istanbul", "Ankara", "Izmir", "Antalya",
            "Konya", "Izmit", "Bursa", "Kayseri", "Malatya", "Trabzon",
            "Antakya", "Erzurum", "Van" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //---no need to call this---
        //setContentView(R.layout.activity_main);


        /*
         * ArrayAdapter Constructor:
         ArrayAdapter<String>(Context context, int resource, String[] objects)
         context:The current context.
		 resource: The resource ID for a layout file containing a TextView to use when instantiating views.
		 objects: The objects to represent in the ListView.
         */
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cities));
    }

    public void onListItemClick(
            ListView parent, View v, int position, long id)
    {
        /*
		parent: The AdapterView where the click happened.
		view: The view within the AdapterView that was clicked
		(this will be a view provided by the adapter)
		position: The position of the view in the adapter.
		id: The row id of the item that was clicked.
		*/
        Toast.makeText(this,
                "You have selected " + cities[position],
                Toast.LENGTH_SHORT).show();
    }
}