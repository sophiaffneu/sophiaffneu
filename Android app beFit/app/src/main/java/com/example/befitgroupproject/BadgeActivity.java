package com.example.befitgroupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BadgeActivity extends AppCompatActivity {

    private TextInputEditText editText_month;
    private TextInputEditText editText_year;
    private Button button;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private String time;
    private String userId;
    private FirebaseUser user;

    private List<String> badgeList;
    private ArrayList<Integer> badgeId;
    private ArrayList<BadgeCard> itemList;

    private BadgeRecyclerViewAdapter recyclerViewAdapter;

    private static final String NUMBER_OF_BADGE = "NUMBER_OF_BADGE";
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";

    private ProgressBar progressBar;
    private TextView textView_day;

    private ArrayList<String> punchCardList;
    private int length;

    private ImageView image;
    private TextView textView_collect;

    private ImageButton toFitnesstest;
    private ImageButton toLaunch;
    private ImageButton toHeart;
    private ImageButton toSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);


        editText_month = findViewById(R.id.editText_month);
        editText_year = findViewById(R.id.editText_year);
        button = findViewById(R.id.button_submit);

        toFitnesstest = findViewById(R.id.imageButtonFitness1);
        toLaunch = findViewById(R.id.imageButtonLaunch1);
        toHeart = findViewById(R.id.imageButtonHeart1);
        toSetting = findViewById(R.id.imageButtonSetting1);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        progressBar = findViewById(R.id.progressBar);
        textView_day = findViewById(R.id.textView_day);
        image = findViewById(R.id.imageView_badge);
        textView_collect = findViewById(R.id.textView_collect);


        toFitnesstest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadgeActivity.this, fitness_test_detail.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        toLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadgeActivity.this, LaunchActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        toHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadgeActivity.this, HeartRateActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        toSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BadgeActivity.this, SettingActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        punchCardList = new ArrayList<String>();
        reference.child(userId).child("PunchCard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.getValue() == null){
                    progressBar.setProgress(0);
                    textView_day.setText("5 ");
                    image.setClickable(false);
                    textView_collect.setVisibility(View.INVISIBLE);
                } else {
                    GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                    punchCardList = snapshot.child("date").getValue(t);
                    length = punchCardList.size();
                    switch(length) {
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
                Toast.makeText(BadgeActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(BadgeActivity.this, "Badge collected!",
                        Toast.LENGTH_SHORT).show();
                punchCardList = new ArrayList<String>();
                punchCardList.add("start");
                reference.child(userId).child("PunchCard").child("date").setValue(punchCardList);
                time = monthYearFromDate(LocalDate.now());
                reference.child(userId).child("badge").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            badgeList = new ArrayList<>();
                            badgeList.add("badge1");
                            reference.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                            reference.child(userId).child("badge").child(time).child("time").setValue(time);

                        }
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.getKey().toString().equals(time)) {
                                reference.child(userId).child("badge").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        GenericTypeIndicator<ArrayList<String>> t =
                                                new GenericTypeIndicator<ArrayList<String>>() {
                                                };
                                        badgeList = ds.child("badgeList").getValue(t);
                                        badgeList.add("badge1");
                                        reference.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                        Toast.makeText(BadgeActivity.this, error.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            } else {
                                badgeList = new ArrayList();
                                badgeList.add("badge1");
                                reference.child(userId).child("badge").child(time).child("badgeList").setValue(badgeList);
                                reference.child(userId).child("badge").child(time).child("time").setValue(time);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                        Toast.makeText(BadgeActivity.this, error.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = editText_month.getText().toString() + editText_year.getText().toString();
                if (time.isEmpty()) {
                    Toast.makeText(BadgeActivity.this, "Please provide the month and year",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Query checkTime = reference.child(userId).child("badge").orderByChild("time").equalTo(time);
                    //reference.child(userId).
                    checkTime.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                Toast.makeText(BadgeActivity.this, "You don't have any badge in this month",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                            Toast.makeText(BadgeActivity.this, error.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });



                    badgeId = new ArrayList<Integer>();
                    reference.child(userId).child("badge").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            time = editText_month.getText().toString() + editText_year.getText().toString();
                            GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                            badgeList = snapshot.child(time).child("badgeList").getValue(t);

                        }
                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
                            Toast.makeText(BadgeActivity.this, error.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });


                    String badgeListCompose = composeBadgeList();
                    if(badgeListCompose != null && badgeListCompose.length() != 0) {
                        String[] badge = badgeListCompose.split("#");
                        for (String s : badge)
                            badgeId.add(Integer.valueOf(s.substring(s.length() - 1, s.length())));
                    }

                    itemList = new ArrayList<BadgeCard>();
                    if(savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_BADGE)) {
                        if(badgeId == null || badgeId.size() == 0) {
                            int size = savedInstanceState.getInt(NUMBER_OF_BADGE);
                            for(int i = 0; i < size; i++) {
                                String badgeId = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                                BadgeCard card = new BadgeCard(Integer.valueOf(badgeId));
                                itemList.add(card);
                            }
                        }
                    } else {
                        for(Integer Id : badgeId) {
                            BadgeCard badgeCard = new BadgeCard(Id.intValue());
                            itemList.add(badgeCard);
                        }

                    }

                    createBadgeRV();
                }
            }


        });

    }

/*
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = badgeId.size();
        outState.putInt(NUMBER_OF_BADGE, size);
        for(int i = 0; i < size; i++)
            outState.putString(KEY_OF_INSTANCE + i + "0", String.valueOf(badgeId));
        super.onSaveInstanceState(outState);
    }

 */

    public String composeBadgeList(){
        String str = "";
        if(badgeList != null){
            for(String pic : badgeList){
                str += pic + '#';
            }
        }
        return str;
    }
    public void createBadgeRV(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        RecyclerView recyclerView = findViewById(R.id.badge_view);
        recyclerViewAdapter = new BadgeRecyclerViewAdapter(itemList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemList.get(position).onItemClick(position);

                recyclerViewAdapter.notifyItemChanged(position);
            }
        };
        recyclerViewAdapter.setOnItemClickListener(itemClickListener);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyy");
        return date.format(formatter);
    }
}
