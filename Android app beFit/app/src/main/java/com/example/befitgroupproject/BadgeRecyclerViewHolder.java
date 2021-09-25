package com.example.befitgroupproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class BadgeRecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView badgeImg;

    public BadgeRecyclerViewHolder(View itemView, final ItemClickListener listener) {
        super(itemView);
        badgeImg = itemView.findViewById(R.id.img_badge2);

    }
}
