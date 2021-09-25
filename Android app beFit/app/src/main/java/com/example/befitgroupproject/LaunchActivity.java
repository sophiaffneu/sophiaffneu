package com.example.befitgroupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PlanRecyclerViewAdapter mMyAdapter;
    private ArrayList<Plans> itemList = new ArrayList<>();
    private FirebaseDatabase rootNode;
    private DatabaseReference reference_1;
    private DatabaseReference reference_2;
    private ArrayList<String> plan_task_List = new ArrayList<>();
    private String userId;
    private int newuser_flag;
    private String current_plan;
    private FirebaseUser user;


    private ArrayList<String> punchCardList;
    private int length;
    private List<String> badgeList;

    private ProgressBar progressBar;
    private TextView textView_day;
    private ImageView image;
    private TextView textView_collect;
    private String time;

    private ImageButton toFitnesstest;
    private ImageButton toBadge;
    private ImageButton toHeart;
    private ImageButton toSetting;
    private ImageButton logout;
    private Plans userCurrentPlan;
    private TextView test;
    private TextView currentPlan;
    private TextView userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        mRecyclerView = findViewById(R.id.present_plan);
        toFitnesstest = findViewById(R.id.imageButton2);
        toBadge = findViewById(R.id.imageButton5);
        toHeart = findViewById(R.id.imageButton3);
        toSetting = findViewById(R.id.imageButton6);
        logout = findViewById(R.id.imageButton4);
        test = findViewById(R.id.textView26);
        currentPlan = findViewById(R.id.textView2);
        userName = findViewById(R.id.textView28);

        mMyAdapter = new PlanRecyclerViewAdapter(itemList);
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(LaunchActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        rootNode = FirebaseDatabase.getInstance();
        reference_1 = rootNode.getReference("Users");
        reference_2 = rootNode.getReference("Plans");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        reference_1.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    userName.setText(name);
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                Toast.makeText(LaunchActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        progressBar = findViewById(R.id.progressBar);
        textView_day = findViewById(R.id.textView_day);
        image = findViewById(R.id.imageView_badge);
        textView_collect = findViewById(R.id.textView_collect);

        punchCardList = new ArrayList<String>();
        reference_1.child(userId).child("PunchCard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    progressBar.setProgress(0);
                    textView_day.setText("5 ");
                    image.setClickable(false);
                    textView_collect.setVisibility(View.INVISIBLE);
                } else {
                    GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
                    };
                    punchCardList = snapshot.child("date").getValue(t);
                    length = punchCardList.size();
                    switch (length) {
                        case 1:
                            progressBar.setProgress(0);
                            textView_day.setText("5 ");
                            image.setClickable(false);
                            textView_collect.setVisibility(View.INVISIBLE);
                            break;
                        case 2:
                            progressBar.setProgress(20);
                            textView_day.setText("4 ");
                            image.setClickable(false);
                            textView_collect.setVisibility(View.INVISIBLE);
                            break;
                        case 3:
                            progressBar.setProgress(40);
                            textView_day.setText("3 ");
                            image.setClickable(false);
                            textView_collect.setVisibility(View.INVISIBLE);
                            break;
                        case 4:
                            progressBar.setProgress(60);
                            textView_day.setText("2 ");
                            image.setClickable(false);
                            textView_collect.setVisibility(View.INVISIBLE);
                            break;
                        case 5:
                            progressBar.setProgress(80);
                            textView_day.setText("1 ");
                            image.setClickable(false);
                            textView_collect.setVisibility(View.INVISIBLE);
                            break;
                        case 6:
                            progressBar.setProgress(100);
                            textView_day.setText("0 ");
                            image.setClickable(true);
                            textView_collect.setVisibility(View.VISIBLE);
                            break;

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(LaunchActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LaunchActivity.this, "Badge collected!",
                        Toast.LENGTH_SHORT).show();
                punchCardList = new ArrayList<String>();
                punchCardList.add("start");
                reference_1.child(userId).child("PunchCard").child("date").setValue(punchCardList);
                time = monthYearFromDate(LocalDate.now());
                reference_1.child(userId).child("badge").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            badgeList = new ArrayList<>();
                            badgeList.add("badge1");
                            reference_1.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                            reference_1.child(userId).child("badge").child(time).child("time").setValue(time);

                        }
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.getKey().toString().equals(time)) {
                                reference_1.child(userId).child("badge").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        GenericTypeIndicator<ArrayList<String>> t =
                                                new GenericTypeIndicator<ArrayList<String>>() {
                                                };
                                        badgeList = ds.child("badgeList").getValue(t);
                                        badgeList.add("badge1");
                                        reference_1.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                        Toast.makeText(LaunchActivity.this, error.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            } else {
                                badgeList = new ArrayList();
                                badgeList.add("badge1");
                                reference_1.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                                reference_1.child(userId).child("badge").child(time).child("time").setValue(time);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(LaunchActivity.this, error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        reference_1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getKey().toString().equals(userId)) {
                        current_plan = dataSnapshot.child(userId).child("Current Plan").getValue(String.class);

                        reference_2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        if (ds.getKey().toString().equals(current_plan)) {
                                            userCurrentPlan = ds.getValue(Plans.class);
                                            itemList.add(userCurrentPlan);
                                            mMyAdapter.notifyDataSetChanged();
                                            break;
                                        }
                                    }
                                    if (itemList.size() == 0) {
                                        mRecyclerView.setVisibility(View.INVISIBLE);
                                        test.setText("You don't have any current plan in progress, " +
                                                "click on the fitness test button to test " +
                                                "your physical fitness and get a plan!");
                                    }
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
                                Toast.makeText(LaunchActivity.this, error.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }


            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(LaunchActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        toFitnesstest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, fitness_test_detail.class);
                startActivity(intent);
            }
        });

        toBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, BadgeActivity.class);
                startActivity(intent);
            }
        });

        toHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, HeartRateActivity.class);
                startActivity(intent);
            }
        });

        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutconfirmation();
            }
        });

    }

    public void otherplans_onclick(View view) {
        Intent intent_1 = new Intent(LaunchActivity.this, other_plans.class);
        startActivity(intent_1);
    }

    public void punchcard_onclick(View view) {
        Intent intent_4 = new Intent(LaunchActivity.this, PunchCardActivity.class);
        startActivity(intent_4);
    }

    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        return date.format(formatter);
    }

    private void logOutconfirmation() {
        new AlertDialog.Builder(this).setTitle("Log out").
                setMessage("Are you sure to log out?").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
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
}