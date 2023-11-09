package com.example.w6_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    private String []  mobileOS = {"Android", "iOS", "WindowsMobile", "Blackberry"};
    private ArrayAdapter<String> mArrayAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.lv_os);

        mArrayAdapter = new MobileOSArrayAdapter(this,mobileOS);
        mListView.setAdapter(mArrayAdapter);


        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //View corresponds to ImageView and TextView  in list_item.xml
                //ImageView id: logo
                //TextView id: label

                TextView tv = (TextView)view.findViewById(R.id.label);
                ImageView img = (ImageView)view.findViewById(R.id.logo);



                Toast.makeText(MainActivity.this,
                        "You have selected " + tv.getText().toString(),
                        Toast.LENGTH_SHORT).show();

                tv.setText(tv.getText().toString()+" * ");
                img.setImageResource(R.drawable.ic_launcher_foreground);

            }
        });
    }
}