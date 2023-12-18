/* Course: 			CENG427 Mobile Application Development
 * Project:			Intent1
 * AVD:				Pixel 2, Android API 28
 * Description:		A simple program that demonstrates the use of Intent to start another Activity.
 * URL:				http://developer.android.com/reference/android/content/Intent.html
 * URL:				http://developer.android.com/reference/android/os/Parcelable.html
 * URL:				http://www.survivingwithandroid.com/2012/09/passing-data-between-activities.html
 *
 * URL:				http://www.vogella.com/articles/JavaSerialization/article.html
 * URL: 			http://mobile.dzone.com/articles/using-android-parcel
 * URL:				http://nomtek.com/developers/parcelable-sending-arbitrary-objects-using-intent-in-android/
 */

package com.example.w101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        startActivity(new Intent("com.example.w101.Second"));

        // This syntax calls another activity by using its class name.
        // It is used more often to call another activity within a project
        // as we know all class names in our project
        //intent = new Intent(this, Second.class);

        // This syntax requires that intent filter must be defined
        // Intent filter is usually used to call a third party program
        // in your application. For example email, browser
        // intent = new Intent("com.example.w101.Second");

        //startActivity(intent);
    }
}