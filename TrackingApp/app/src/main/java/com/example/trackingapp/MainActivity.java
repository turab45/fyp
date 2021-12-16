package com.example.trackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final int ERROR_DIALOG_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean isServicesOk(){
        
        return  false;
    }
}