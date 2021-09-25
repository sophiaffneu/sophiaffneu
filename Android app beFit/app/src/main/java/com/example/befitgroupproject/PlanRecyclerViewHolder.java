package com.example.befitgroupproject;


import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

public class PlanRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView description;
    TextView warmUp;
    TextView cardioTraining;
    TextView upperBody;
    TextView lowerBody;
    TextView coolDown;


    public PlanRecyclerViewHolder(View itemView) {
        super(itemView);
        description = itemView.findViewById(R.id.textView14);
        warmUp = itemView.findViewById(R.id.textView20);
        cardioTraining = itemView.findViewById(R.id.textView21);
        upperBody = itemView.findViewById(R.id.textView22);
        lowerBody = itemView.findViewById(R.id.textView23);
        coolDown = itemView.findViewById(R.id.textView24);

    }
}
