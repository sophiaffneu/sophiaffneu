package com.example.befitgroupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class other_plans extends AppCompatActivity {
    private String target_plan;
    private int target_number;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_plans);

        Button back = findViewById(R.id.button5);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_0 = new Intent(other_plans.this,LaunchActivity.class);
                startActivity(intent_0);
            }
        });
    }

    public void plan1_onclick(View view){
        target_plan = "Plan1";
        target_number = 1;
        goto_next_activity(target_plan, target_number, userId,view);
    }

    public void plan2_onclick(View view){
        target_plan = "Plan2";
        target_number = 2;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan3_onclick(View view){
        target_plan = "Plan3";
        target_number = 3;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan4_onclick(View view){
        target_plan = "Plan4";
        target_number = 4;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan5_onclick(View view){
        target_plan = "Plan5";
        target_number = 5;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan6_onclick(View view){
        target_plan = "Plan6";
        target_number = 6;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan7_onclick(View view){
        target_plan = "Plan7";
        target_number = 7;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan8_onclick(View view){
        target_plan = "Plan8";
        target_number = 8;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void plan9_onclick(View view){
        target_plan = "Plan9";
        target_number = 9;
        goto_next_activity(target_plan, target_number, userId, view);
    }

    public void goto_next_activity(String m, int a, String number, View view){
        Intent i = new Intent(other_plans.this, display_otherplans.class);
        i.putExtra("target_plan", m);
        i.putExtra("target_number", a);
        startActivity(i);
    }

}