package com.example.w102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String [] numbers = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten" };
    private int usernumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = (TextView) findViewById(R.id.tvRes);

        // ********* If Direct data is passed *********
        /*Intent intent = getIntent();
        usernumber = Integer.parseInt(intent.getStringExtra("num") );
        if (usernumber <= 0 || usernumber > 10)
            tv.setText(" " + "Wrong Number");
        else
            tv.setText(" " + numbers[usernumber - 1]);*/


        // ********* If Bundle is used ************
        // getIntent() method returns the Activity that started this Activity
        // use getExtras() method to obtain the Bundle object


		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			// getString() method retrieves the value which is associated with
			// num key from the Bundle object
			usernumber = Integer.parseInt(extras.getString("num"));

			if (usernumber <= 0 || usernumber > 10)
				tv.setText(" " + "Wrong Number");
			else
				tv.setText(" " + numbers[usernumber - 1]);
		}
    }
}