package com.example.googledirectionsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        if (view.getId() == R.id.admin_btn){
            Toast.makeText(this, "Admin clicked", Toast.LENGTH_SHORT).show();
        }else if (view.getId() == R.id.driver_btn){
            Toast.makeText(this, "Driver clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DriverDashboard.class);
            startActivity(intent);
        }else if (view.getId() == R.id.student_btn){
            Toast.makeText(this, "Student clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
    }
}