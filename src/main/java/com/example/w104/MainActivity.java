/* Course: 			CENG427 Mobile Application Development
 * Project:			Intent4

 * Description:		A simple program that demonstrates how to pass complex data from the main
 * 					Activity to the second Activity via Intent and Parcelable interface.
 * URL:				http://developer.android.com/reference/android/content/Intent.html
 * URL:				http://developer.android.com/reference/android/os/Parcelable.html
 * URL:				http://www.survivingwithandroid.com/2012/09/passing-data-between-activities.html
 *
 * URL:				http://www.vogella.com/articles/JavaSerialization/article.html
 * URL: 			http://mobile.dzone.com/articles/using-android-parcel
 * URL:				http://nomtek.com/developers/parcelable-sending-arbitrary-objects-using-intent-in-android/
 */
package com.example.w104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        // Create SerializableLaptop object
        SerializableLaptop mSerializableLaptop = new SerializableLaptop();
        mSerializableLaptop.setId(1);
        mSerializableLaptop.setBrand("Macbook Air");
        mSerializableLaptop.setPrice(999);

        // A Bitmap cannot be serialized. Hence it is converted into a byte array
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.macbook_air);
        ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, mByteArrayOutputStream);
        byte [] mByte = mByteArrayOutputStream.toByteArray();

        mSerializableLaptop.setByteArray(mByte);

        Intent intent = new Intent(this, ReceiveObjActivity.class);
        // Creating a bundle object
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("laptop", mSerializableLaptop);
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}