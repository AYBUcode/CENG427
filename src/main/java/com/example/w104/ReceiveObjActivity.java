package com.example.w104;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveObjActivity extends AppCompatActivity {
    private TextView descTxt;
    private ImageView imageView;
    private Bitmap mBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_obj);

        descTxt = (TextView) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.imv1);

        Bundle mBundle = getIntent().getExtras();
        SerializableLaptop mSerializableLaptop = (SerializableLaptop) mBundle.getSerializable("laptop");

        display(mSerializableLaptop);
    }

    private void display(SerializableLaptop laptop) {
        String desc = laptop.getId() + ": " + laptop.getBrand() + "\n"
                + laptop.getPrice();
        descTxt.setText(desc);

        byte[] mByte = laptop.getByteArray();
        // Converting the byte array into a Bitmap
        mBitmap = BitmapFactory.decodeByteArray(mByte, 0, mByte.length);

        imageView.setImageBitmap(mBitmap);
    }
}