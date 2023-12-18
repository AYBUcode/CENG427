package com.example.w105;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn1);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Laptop laptop = new Laptop(1,"Macbook Air",999);

        // if image placed inside res/drawable folder
        // InputStream is = getResources().openRawResource(R.drawable.macbook_air);

        // if image placed inside res/raw folder
        InputStream is = getResources().openRawResource(R.raw.macbook_air);
        Bitmap bitmap = BitmapFactory.decodeStream(is);

        laptop.setImageBitmap(bitmap);
        Intent intent = new Intent(getApplicationContext(), ReceiveObjActivity.class);

        intent.putExtra("laptop", laptop);

        // Start next activity
        startActivity(intent);
    }
}