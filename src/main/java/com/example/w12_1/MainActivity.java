package com.example.w12_1;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.w12_1.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TextView textViewDB, textViewNotif, textViewHome;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //configs
        listView = (ListView) findViewById(R.id.listView);
        textViewDB = (TextView) findViewById(R.id.text_dashboard);
        textViewHome = (TextView) findViewById(R.id.text_home);
        textViewNotif = (TextView) findViewById(R.id.text_notifications);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        /*if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED) {
            System.out.println("permission is granted");

            //content provider
            ContentResolver contentResolver = getContentResolver();
            String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                    projection,
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME);
            if (cursor == null) {
                System.out.println("cursor is null");
                return;
            }
            ArrayList<String> contacts = new ArrayList<String>();
            String colmnIndex = ContactsContract.Contacts.DISPLAY_NAME;
            while (cursor.moveToNext()) {
                int index = cursor.getColumnIndex(colmnIndex);
                if (index == -1) continue;
                contacts.add(cursor.getString(index));
            }
            cursor.close();

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1, contacts);
            listView.setAdapter(adapter);
        } else {
            System.out.println("permission NOT granted");
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, 1);

        }*/
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                System.out.println("item: " + item);
                if (item.getItemId() == R.id.navigation_home) {
                    textViewHome.setText("Home 2");
                }
                if (item.getItemId() == R.id.navigation_dashboard) {
                    textViewHome.setText("Dashboard 2");
                }
                if (item.getItemId() == R.id.navigation_notifications) {
                    textViewHome.setText("Notifications 2");
                }
            }
        });
        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                System.out.println("item: " + item);
                if (item.getItemId() == R.id.navigation_home) {
                    textViewHome.setText("Home");
                }
                if (item.getItemId() == R.id.navigation_dashboard) {
                    textViewHome.setText("Dashboard");
                    if(ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.READ_CONTACTS") == PackageManager.PERMISSION_GRANTED){
                        System.out.println("permission is granted");

                        //content provider
                        ContentResolver contentResolver = getContentResolver();
                        String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
                        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                                projection,
                                null,
                                null,
                                ContactsContract.Contacts.DISPLAY_NAME);
                        if (cursor == null) {
                            System.out.println("cursor is null");
                            return false;
                        }
                        ArrayList<String> contacts = new ArrayList<String>();
                        String colmnIndex = ContactsContract.Contacts.DISPLAY_NAME;
                        while(cursor.moveToNext()){
                            int index = cursor.getColumnIndex(colmnIndex);
                            if (index == -1) continue;
                            contacts.add(cursor.getString(index));
                        }
                        cursor.close();

                        ArrayAdapter<String> adapter =
                                new ArrayAdapter<String>(MainActivity.this,
                                        android.R.layout.simple_list_item_1, contacts);
                        listView.setAdapter(adapter);
                    }
                    else{
                        System.out.println("permission NOT granted");
                        //requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 1);
                        Snackbar.make(findViewById(R.id.navigation_dashboard), "Permission not granted", Snackbar.LENGTH_INDEFINITE)
                                .setAction("GIVE PERMISSION", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                                "android.permission.READ_CONTACTS")) {
                                            ActivityCompat.requestPermissions(MainActivity.this,
                                                    new String[]{"android.permission.READ_CONTACTS"},
                                                    1);
                                        } else {
                                            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            //intent.setData(Uri.parse("package:" + getPackageName()));
                                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                                            intent.setData(uri);
                                            MainActivity.this.startActivity(intent);
                                        }

                                    }
                                }).show();

                    }

                }
                if (item.getItemId() == R.id.navigation_notifications) {
                    textViewHome.setText("Notifications");
                }
                return true;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        System.out.println("id: " + id);
        if (id == R.id.navigation_home) {
            return true;
        }
        if (id == R.id.navigation_dashboard) {
            Snackbar.make(findViewById(R.id.navigation_dashboard), "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        if (id == R.id.navigation_notifications) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}