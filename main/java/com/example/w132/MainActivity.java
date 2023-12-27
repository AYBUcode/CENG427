package com.example.w132;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //using static resources
        textBox = (EditText) findViewById(R.id.txtText1);
        try {
        InputStream is = this.getResources().openRawResource(R.raw.textfile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        String output = "";

            while ((str = br.readLine()) != null) {
                output += str;
            }
            is.close();
            br.close();
            textBox.setText(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickSave(View view) {
        String str = textBox.getText().toString();
        try
        {
            FileOutputStream fOut =
                    openFileOutput("textfile.txt",
                            MODE_PRIVATE);
            OutputStreamWriter osw = new
                    OutputStreamWriter(fOut);
            //---write the string to the file---
            osw.write(str);
            osw.flush();
            osw.close();
            //---display file saved message---
            Toast.makeText(getBaseContext(),
                    "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
            //---clears the EditText---
            textBox.setText("");
        }
        catch (Exception ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void onClickLoad(View view) {
        try
        {
            FileInputStream fIn =
                    openFileInput("textfile.txt");
            InputStreamReader isr = new
                    InputStreamReader(fIn);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;
            while ((charRead = isr.read(inputBuffer))>0)
            {
            //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0,
                                charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            //---set the EditText to the text that has been
            // read---
            textBox.setText(s);
            Toast.makeText(getBaseContext(),
                    "File loaded successfully!",
            Toast.LENGTH_SHORT).show();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}