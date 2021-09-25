package com.example.befitgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class display_otherplans extends AppCompatActivity {
    private String userId;
    private TextView plan_number_view;
    private TextView task1_view;
    private TextView task2_view;
    private TextView task3_view;
    private TextView task4_view;
    private TextView task5_view;
    private TextView task6_view;
    private int target_plan_number;
    private String target_plan;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference_0;
    private DatabaseReference reference_1;
    private Plans userCurrentPlan;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_otherplans);
        rootNode = FirebaseDatabase.getInstance();
        reference_0 = rootNode.getReference("Plans");
        reference_1 = rootNode.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        plan_number_view = findViewById(R.id.target_plan_number);
        task1_view = findViewById(R.id.task1);
        task2_view = findViewById(R.id.task2);
        task3_view = findViewById(R.id.task3);
        task4_view = findViewById(R.id.task4);
        task5_view = findViewById(R.id.task5);
        task6_view = findViewById(R.id.task6);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            target_plan = extras.getString("target_plan");
            target_plan_number = extras.getInt("target_number");
        }


        reference_0.child(target_plan).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userCurrentPlan = dataSnapshot.getValue(Plans.class);
                plan_number_view.setText(String.valueOf(target_plan_number));
                task1_view.setText(userCurrentPlan.getDescription());
                task2_view.setText(userCurrentPlan.getWarmUp());
                task3_view.setText(userCurrentPlan.getCardioTraining());
                task4_view.setText(userCurrentPlan.getUpperBody());
                task5_view.setText(userCurrentPlan.getLowerBody());
                task6_view.setText(userCurrentPlan.getCoolDown());
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(display_otherplans.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void save_and_use(View view){
        reference_1.child(userId).child("Current Plan").setValue(target_plan);

        new AlertDialog.Builder(display_otherplans.this).setTitle("you've selected a plan and saved it.").
                setMessage(" click OK to get started!").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Jump to punch card page
                        Intent intent = new Intent(display_otherplans.this, LaunchActivity.class);
                        startActivity(intent);
                    }
                }).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }

    public void view_otherplans_onclick(View view){
        Intent i7 = new Intent(display_otherplans.this,other_plans.class);
        i7.putExtra("userId",userId);
        startActivity(i7);
    }
}