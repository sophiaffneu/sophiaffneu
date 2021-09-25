package com.example.befitgroupproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PunchCardActivity extends AppCompatActivity implements CalendarViewAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private boolean flag1 = false;
    private boolean flag2 = false;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ArrayList<String> dateList;
    private String userId;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punchcard);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalendarViewAdapter calendarAdapter = new CalendarViewAdapter(daysInMonth, flag2,this, userId);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return  daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    private String yearMonthDateFromDate(LocalDate date)
    {
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter_1);
    }

    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        LocalDate date = LocalDate.now();
        if (!date.equals(selectedDate)){
            flag2 = true;
        } else {
            flag2 = false;
        }
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        LocalDate date = LocalDate.now();
        if (!date.equals(selectedDate)){
            flag2 = true;
        } else {
            flag2 = false;
        }
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText, TextView view)
    {
        if(!dayText.equals(""))
        {
            String date = dayText + monthYearText.getText().toString();
            String today = LocalDate.now().getDayOfMonth() + monthYearFromDate(LocalDate.now());
            if(date.equals(today) && !flag1){
                new AlertDialog.Builder(this).setTitle("Punch Card").
                        setMessage("Do you want to punch card for today").
                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                view.setBackgroundColor(Color.parseColor("#C88464"));


                                dateList = new ArrayList();
                                reference.child(userId).child("PunchCard").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};

                                        String date_1 = yearMonthDateFromDate(LocalDate.now());
                                        String[] todayList = date_1.split("-");
                                        int year_today = Integer.valueOf(todayList[0]);
                                        int month_today = Integer.valueOf(todayList[1]);
                                        int day_today = Integer.valueOf(todayList[2]);


                                        dateList = snapshot.child("date").getValue(t);
                                        if (dateList.size() == 1){
                                            dateList.add(date_1);
                                            reference.child(userId).child("PunchCard").child("date").setValue(dateList);
                                        } else {
                                            String lastDate = dateList.get(dateList.size()-1);
                                            String[] lastDateList = lastDate.split("-");
                                            int year_last = Integer.valueOf(lastDateList[0]);
                                            int month_last = Integer.valueOf(lastDateList[1]);
                                            int day_last = Integer.valueOf(lastDateList[2]);

                                            if (year_today == year_last && month_today == month_last && day_today - day_last == 1) {
                                                dateList.add(date_1);
                                                reference.child(userId).child("PunchCard").child("date").setValue(dateList);
                                                Toast.makeText(PunchCardActivity.this, "Punch card succeed", Toast.LENGTH_SHORT).show();

                                            } else if (year_today == year_last && month_today == month_last && day_today == day_last){
                                                Toast.makeText(PunchCardActivity.this, "Already punched!", Toast.LENGTH_SHORT).show();

                                            } else {
                                                dateList = new ArrayList();
                                                dateList.add("start");
                                                dateList.add(date_1);
                                                reference.child(userId).child("PunchCard").child("date").setValue(dateList);
                                                Toast.makeText(PunchCardActivity.this, "Punch card succeed", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                        setMonthView();

                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {
                                        Toast.makeText(PunchCardActivity.this, error.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                                flag1 = true;
                                dialog.dismiss();
                            }
                        }).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            } else if (date.equals(today) && flag1){
                Toast.makeText(PunchCardActivity.this, "Already punched!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PunchCardActivity.this, "Can only punch card for today. Not any other day.", Toast.LENGTH_SHORT).show();
            }


        }

    }
}








