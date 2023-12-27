package com.example.w13_3;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.w13_3.databinding.ActivityDbactivityBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class DBActivity extends AppCompatActivity {
    private ActivityDbactivityBinding binding;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    Bitmap selectedImage;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);
        binding = ActivityDbactivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        registerLauncher();

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");

        if (info.equals("new")){
            //new person
            binding.nameText.setText("");
            binding.locationText.setText("");
            binding.dateText.setText("");
            binding.button.setVisibility(View.VISIBLE); //visible
            binding.imageView.setImageResource(R.drawable.selectimage);
        }else{
            //old person
            String searchName = intent.getStringExtra("personName");
            binding.button.setVisibility(View.INVISIBLE); //invisible
            try {
                database = this.openOrCreateDatabase("MyTable", MODE_PRIVATE, null);
                Cursor cursor = database.rawQuery("SELECT * FROM myTable WHERE name = ?", new String[]{searchName});
                int nameIx = cursor.getColumnIndex("name");
                int locationIx = cursor.getColumnIndex("location");
                int dateIx = cursor.getColumnIndex("date");
                int imageIx = cursor.getColumnIndex("image");
                while (cursor.moveToNext()) {
                    binding.nameText.setText(cursor.getString(nameIx));
                    binding.locationText.setText(cursor.getString(locationIx));
                    binding.dateText.setText(cursor.getString(dateIx));
                    byte[] byteArray = cursor.getBlob(imageIx);
                    Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    binding.imageView.setImageBitmap(image);
                }
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void save(View view) {
        //get data from form
        String name = binding.nameText.getText().toString();
        String location = binding.locationText.getText().toString();
        String date = binding.dateText.getText().toString();
        //convert image to byte array
        Bitmap smallImage = makeSmaller(selectedImage, 300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        //save to database
        try {
            database = this.openOrCreateDatabase("MyTable", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS myTable (id INTEGER PRIMARY KEY, name VARCHAR, location VARCHAR, date VARCHAR, image BLOB)");
            SQLiteStatement statement = database.compileStatement("INSERT INTO myTable (name, location, date, image) VALUES (?, ?, ?, ?)");
            statement.bindString(1, name);
            statement.bindString(2, location);
            statement.bindString(3, date);
            statement.bindBlob(4, byteArray);
            statement.execute();
            //database.execSQL("INSERT INTO myTable (name, location, date, image) VALUES ('" + name + "', '" + location + "', '" + date + "', '" + byteArray + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //finish(); --> go back to previous activity
        Intent intent = new Intent(this, MainActivity.class);
        //close all other activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public Bitmap makeSmaller(Bitmap image, int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();

        float ratio = (float) width / (float) height;

        if (ratio > 1){
            //landscape
            width = maxSize;
            height = (int) (width / ratio);
        }
        else{
            //portrait
            height = maxSize;
            width = (int) (height * ratio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void selectImage(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            System.out.println("version is tiramisu");
            //Android 33+
            if (ContextCompat.checkSelfPermission(DBActivity.this, "android.permission.READ_MEDIA_IMAGES") == PackageManager.PERMISSION_GRANTED) {
                //permission is granted
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, 1);
                activityResultLauncher.launch(i);

            } else {
                //permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale(DBActivity.this, "android.permission.READ_MEDIA_IMAGES")) {
                    //show explanation
                    Snackbar.make(view, "Permission needed for external storage", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ActivityCompat.requestPermissions(DBActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                            requestPermissionLauncher.launch("android.permission.READ_MEDIA_IMAGES");
                        }
                    }).show();

                } else {
                    //no explanation needed
                    //ActivityCompat.requestPermissions(DBActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                    requestPermissionLauncher.launch("android.permission.READ_MEDIA_IMAGES");
                }
            }
        }
        else{
            System.out.println("version is not tiramisu");
            //Android 32-
            if (ContextCompat.checkSelfPermission(DBActivity.this, "android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
                //permission is granted
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, 1);
                activityResultLauncher.launch(i);

            } else {
                //permission is not granted
                if (ActivityCompat.shouldShowRequestPermissionRationale(DBActivity.this, "android.permission.READ_EXTERNAL_STORAGE")) {
                    //show explanation
                    Snackbar.make(view, "Permission needed for external storage", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ActivityCompat.requestPermissions(DBActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                            requestPermissionLauncher.launch("android.permission.READ_EXTERNAL_STORAGE");
                        }
                    }).show();

                } else {
                    //no explanation needed
                    //ActivityCompat.requestPermissions(DBActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                    requestPermissionLauncher.launch("android.permission.READ_EXTERNAL_STORAGE");
                }
            }
        }


    }

    private void registerLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    //image uri
                    Uri imageData = data.getData();
                    //binding.imageView.setImageURI(imageData);
                    try {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P){
                        ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                        selectedImage = ImageDecoder.decodeBitmap(source);
                        binding.imageView.setImageBitmap(selectedImage);
                        }
                        else{
                            selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                            binding.imageView.setImageBitmap(selectedImage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    //permission is granted
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(i);
                } else {
                    //permission is not granted
                    Snackbar.make(binding.getRoot(), "Permission needed for external storage", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(DBActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
                        }
                    }).show();
                }
            }
        });
    }
}