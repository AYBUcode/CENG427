package com.example.w5_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Project[] projects = {
                new Project("Project 1", "Description 1", R.drawable.getting_started),
                new Project("Project 2", "Description 2", R.drawable.quote),
                new Project("Project 3", "Description 3", R.drawable.calculator),
                new Project("Project 4", "Description 4", R.drawable.tape),
                new Project("Project 5", "Description 5", R.drawable.hungry_developer),
                new Project("Project 6", "Description 6", R.drawable.cat)
        };

        ProjectAdapter adapter = new ProjectAdapter(projects);
        RecyclerView recyclerView = findViewById(R.id.rv_os);
        recyclerView.setAdapter(adapter);
    }
}