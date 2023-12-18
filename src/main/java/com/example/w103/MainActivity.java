package com.example.w103;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etnum1;
    private EditText etnum2;
    private Intent intent;

    private int request_Code = 1;	// ID for an Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnum1 = (EditText) findViewById(R.id.et1);
        etnum2 = (EditText) findViewById(R.id.et2);
    }

    public void onClick(View view) {
        intent = new Intent(this, SecondActivity.class);

        /*
         * Use Bundle object to carry data to the target activity Bundle object
         * is basically a dictionary object that enables you to set data in
         * key/value pairs.
         */

        Bundle mBundle = new Bundle();
        mBundle.putInt("num1", Integer.parseInt(etnum1.getText().toString()));
        mBundle.putInt("num2", Integer.parseInt(etnum2.getText().toString()));

        intent.putExtras(mBundle);

        /*
         * To call activity and wait for result to be returned from it. It also
         * pass request code as integer value that identifies an activity you
         * are calling. This is needed because when an activity returns a value,
         * you must have a way to identify it. For example, you may be calling
         * multiple activities at the same time and some activities may not
         * return immediately (for example, waiting for a reply from a server).
         * When an activity returns, you need this request code to determine
         * which activity is actually returned.
         */
        startActivityForResult(intent, request_Code);
    }

    /*
     * In the calling activity, implement onActivityResult(...) method, which is
     * called whenever an activity returns the returned result is passed in via
     * the data argument and you obtain its details through the getData() method
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK)
                Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}