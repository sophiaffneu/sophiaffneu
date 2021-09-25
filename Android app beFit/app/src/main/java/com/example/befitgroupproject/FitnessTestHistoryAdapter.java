package com.example.befitgroupproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FitnessTestHistoryAdapter extends RecyclerView.Adapter<FitnessTestHistoryAdapter.ViewHolder> {
    private List<FitnessTestResult> resultList;
    private Context context;

    public FitnessTestHistoryAdapter(Context context, List<FitnessTestResult> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @NotNull
    @Override
    public FitnessTestHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FitnessTestHistoryAdapter.ViewHolder holder, int position) {
        holder.date.setText("Test Date: " + resultList.get(position).date);
        holder.BMI.setText("BMI: " + resultList.get(position).BMI);
        holder.coreStrength.setText("Core Strength: " + resultList.get(position).coreStrength);
        holder.pushUp.setText("Push-up: " + resultList.get(position).pushUp);
        holder.running.setText("Running: " + resultList.get(position).running);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView BMI;
        TextView coreStrength;
        TextView pushUp;
        TextView running;

        public ViewHolder(@NonNull @NotNull View itemView
        ) {
            super(itemView);
            date = itemView.findViewById(R.id.textView10);
            BMI = itemView.findViewById(R.id.textView16);
            coreStrength = itemView.findViewById(R.id.textView17);
            pushUp = itemView.findViewById(R.id.textView18);
            running = itemView.findViewById(R.id.textView19);
        }
    }
}
