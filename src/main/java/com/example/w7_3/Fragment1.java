package com.example.w7_3;

import android.preference.PreferenceFragment;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Fragment1 extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
