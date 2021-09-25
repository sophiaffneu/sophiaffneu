package com.example.befitgroupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class PlanRecyclerViewAdapter extends RecyclerView.Adapter<PlanRecyclerViewHolder> {

    private final ArrayList<Plans> itemList;
    public PlanRecyclerViewAdapter(ArrayList<Plans> itemList) {
        this.itemList = itemList;
    }

    public PlanRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        PlanRecyclerViewHolder myViewHolder = new PlanRecyclerViewHolder(view);
        return myViewHolder;
    }

    public void onBindViewHolder(PlanRecyclerViewHolder holder, int position) {
        holder.description.setText(itemList.get(position).description);
        holder.warmUp.setText(itemList.get(position).warmUp);
        holder.cardioTraining.setText(itemList.get(position).cardioTraining);
        holder.upperBody.setText(itemList.get(position).upperBody);
        holder.lowerBody.setText(itemList.get(position).lowerBody);
        holder.coolDown.setText(itemList.get(position).coolDown);
    }

    public int getItemCount() {
        return itemList.size();
    }

}