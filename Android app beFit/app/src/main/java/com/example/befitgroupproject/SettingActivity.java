package com.example.befitgroupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    private String email;
    private ImageButton toFitness;
    private ImageButton toHeart;
    private ImageButton toBadge;
    private ImageButton toLaunch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toFitness = findViewById(R.id.imageButtonFitness5);
        toHeart = findViewById(R.id.imageButtonHeart5);
        toBadge = findViewById(R.id.imageBadge5);
        toLaunch = findViewById(R.id.imageButtonLaunch5);

        toFitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, fitness_test_detail.class);
                startActivity(intent);
            }
        });

        toHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, HeartRateActivity.class);
                startActivity(intent);
            }
        });

        toLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, LaunchActivity.class);
                startActivity(intent);
            }
        });

        toBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, BadgeActivity.class);
                startActivity(intent);
            }
        });

    }
}
