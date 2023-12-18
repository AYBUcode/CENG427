package com.example.w105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveObjActivity extends AppCompatActivity {
    private TextView descTxt;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_obj);

        descTxt = (TextView) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.icon);

        Intent intent = getIntent();

        Laptop laptop = (Laptop) intent.getParcelableExtra("laptop");
        display(laptop);
    }

    private void display(Laptop laptop) {
        String desc = laptop.getId() + ": " + laptop.getBrand() + "\n"
                + laptop.getPrice();
        descTxt.setText(desc);
        imageView.setImageBitmap(laptop.getImageBitmap());
    }
}