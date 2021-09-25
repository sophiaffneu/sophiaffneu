package com.example.befitgroupproject;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

class CalendarViewAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private boolean flag;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private ArrayList<String> datePunched;
    private String phoneNumber;


    public CalendarViewAdapter(ArrayList<String> daysOfMonth, boolean flag, OnItemListener onItemListener, String phoneNumbe)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.flag = flag;
        this.phoneNumber = phoneNumbe;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
        LocalDate date = LocalDate.now();
        if (holder.dayOfMonth.getText().toString().equals(Integer.toString(date.getDayOfMonth())) && !flag){
            holder.dayOfMonth.setBackgroundColor(Color.parseColor("#F8EDE0"));
        }
        readData(new MyCallback() {
            @Override
            public void onCallback(ArrayList value) {
                Iterator<String> iterator = value.iterator();
                iterator.next();
                while (iterator.hasNext()){
                    String date_punched = iterator.next().substring(8);
                    if (date_punched.substring(0,1).equals("0")){
                        date_punched = date_punched.substring(1);
                    }

                    if (holder.dayOfMonth.getText().toString().equals(date_punched) && !flag){
                        holder.dayOfMonth.setBackgroundColor(Color.parseColor("#C88464"));
                    }

                }
            }
        });

    }

    public void readData(MyCallback myCallback) {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        reference.child(phoneNumber).child("PunchCard").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.getValue() == null) {
                    datePunched = new ArrayList<>();
                    datePunched.add("start");
                    reference.child(phoneNumber).child("PunchCard").child("date").setValue(datePunched);

                } else {
                    GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                    datePunched = snapshot.child("date").getValue(t);
                }
                myCallback.onCallback(datePunched);

            }
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }

        });
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText, TextView view);
    }

    public interface MyCallback {
        void onCallback(ArrayList value);
    }
}
