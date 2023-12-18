/* Course: 			CENG427 Mobile Application Development
 * Project:			Intent2
 * Description:		A simple program that demonstrates how to pass simple data from one
 * 					Activity to another one via Intent.
 * URL:				http://developer.android.com/reference/android/content/Intent.html
 * URL:				http://developer.android.com/reference/android/os/Parcelable.html
 * URL:				http://www.survivingwithandroid.com/2012/09/passing-data-between-activities.html
 *
 * URL:				http://www.vogella.com/articles/JavaSerialization/article.html
 * URL: 			http://mobile.dzone.com/articles/using-android-parcel
 * URL:				http://nomtek.com/developers/parcelable-sending-arbitrary-objects-using-intent-in-android/
 */
package com.example.w102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etnum;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnum = (EditText) findViewById(R.id.et);
    }

    public void onClick(View v) {

        /* Intent supports four ways to pass data:
         * 1. Direct: Put data into an Intent directly
         * 2. Bundle: Create a bundle and set the data here
         * 3. Parcelable: It is a way of "serializing" our object.
         * 4. Serializable: Another way to "serialize" data. Its performance is
         * 					slower than Parcelable.
         *
         * First two options are used for passing simple data types (int, double, String etc)
         * For complex data i.e., objects of a class third and fourth option should be used
         */


        // ********  Direct data passing *********
        /*intent = new Intent(this, SecondActivity.class);
        intent.putExtra("num", etnum.getText().toString());
        startActivity(intent);*/


        // ******** Data passing using a Bundle *********
        intent = new Intent(this, SecondActivity.class);

        Bundle mBundle = new Bundle();
        mBundle.putString("num", etnum.getText().toString());
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}