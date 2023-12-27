package com.example.w13_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.w13_3.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Person> personArrayList;
    private PersonAdaptor personAdaptor;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //configs
        personArrayList = new ArrayList<Person>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        personAdaptor = new PersonAdaptor(personArrayList);
        binding.recyclerView.setAdapter(personAdaptor);
        id = 100;
        getData();
    }

    private void getData() {
        try {
            //get data from database
            SQLiteDatabase database = this.openOrCreateDatabase("MyTable", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM myTable", null);
            int nameIx = cursor.getColumnIndex("name");
            int locationIx = cursor.getColumnIndex("location");
            /*
            int dateIx = cursor.getColumnIndex("date");
            int imageIx = cursor.getColumnIndex("image");
            */
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIx);
                String location = cursor.getString(locationIx);
                /*
                String date = cursor.getString(dateIx);
                byte[] byteArray = cursor.getBlob(imageIx);
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                */
                Person person = new Person(name, location, id++);
                personArrayList.add(person);

            }
            personAdaptor.notifyDataSetChanged();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //bind menu to activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mif = getMenuInflater();
        mif.inflate(R.menu.db_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle menu item selection
        if (item.getItemId() == R.id.menu1) {
            Intent i = new Intent(this, DBActivity.class);
            i.putExtra("info", "new");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}