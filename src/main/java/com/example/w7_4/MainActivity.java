package com.example.w7_4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);//Interaction example
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        //---get the current display info---
        //WindowManager wm = getWindowManager();//-> to check the orientation opt.2
        //Display d = wm.getDefaultDisplay();//-> to check the orientation opt.2
        int orientation = getResources().getConfiguration().orientation;//opt.1
        //if (d.getWidth() > d.getHeight()) //-> to check the orientation opt.2
        if(orientation == Configuration.ORIENTATION_LANDSCAPE)//opt.1
        {
        //---landscape mode---
            Fragment1 fragment1 = new Fragment1();
        // android.R.id.content refers to the content
        // view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, fragment1);
        }
        else
        {
        //---portrait mode---
            Fragment2 fragment2 = new Fragment2();
            fragmentTransaction.replace(
                    android.R.id.content, fragment2);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}