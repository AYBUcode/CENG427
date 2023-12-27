/* Course: 			CENG427 Mobile Application Development
 * AVD:				Pixel 2, Android API 28
 * Program: 		SharedPreferences
 * Description:		Android provides the SharedPreferences object to help save
 * 					simple application data. Data is saved in the form of key/value
 * 					pairs in an XML file.
 * URL:				http://developer.android.com/reference/android/content/SharedPreferences.html
 * URL:				http://mobile.tutsplus.com/tutorials/android/android-application-preferences/
 */

package com.example.w13_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLoad(View view) {
        Intent i = new Intent(MainActivity.this, AppPreferenceActivity.class);
        startActivity(i);
    }

    public void onClickDisplay(View view) {
        SharedPreferences appPrefs =
                getSharedPreferences("com.example.w13_1_preferences",
                        MODE_PRIVATE);
        /*SharedPreferences appPrefs =
                getSharedPreferences("appPreferences", MODE_PRIVATE);*/
        DisplayText(appPrefs.getString("editTextPref", ""));
    }
    public void onClickModify(View view) {
        SharedPreferences appPrefs =
                getSharedPreferences("com.example.w13_1_preferences",
                        MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = appPrefs.edit();
        prefsEditor.putString("editTextPref",
                ((EditText) findViewById(R.id.txtString)).getText().toString());
        prefsEditor.commit();
    }
    private void DisplayText(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
}
