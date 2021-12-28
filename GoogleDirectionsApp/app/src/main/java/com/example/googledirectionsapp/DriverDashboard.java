package com.example.googledirectionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DriverDashboard extends AppCompatActivity {

    FloatingActionButton navigationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        startNavigation();
    }

    public void startNavigation(){
        navigationBtn = findViewById(R.id.navigation_launch_btn);

        navigationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=24.7014,70.1783&mode=d"));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

//                Intent intent = new Intent(DriverDashboard.this, NavigationActivity.class);
//                startActivity(intent);
            }
        });
    }
}