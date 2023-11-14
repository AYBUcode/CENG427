package com.example.w7_00;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondaryFragment extends Fragment {

    public SecondaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_secondary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button2);
        TextView textView = view.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFirst(view);
            }
        });

        if (getArguments() != null){
            int val = SecondaryFragmentArgs.fromBundle(getArguments()).getVal();
            textView.setText("Val: " + val);
        }
    }

    public void goToFirst(View view){
        NavController navController = NavHostFragment.findNavController(this);

        navController.navigate(R.id.action_secondaryFragment_to_primeFragment);
    }
}