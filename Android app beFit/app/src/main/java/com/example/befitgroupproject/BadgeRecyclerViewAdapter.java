package com.example.befitgroupproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BadgeRecyclerViewAdapter extends RecyclerView.Adapter<BadgeRecyclerViewHolder> {
    private final ArrayList<BadgeCard> badgeList;
    private ItemClickListener listener;

    public BadgeRecyclerViewAdapter(ArrayList<BadgeCard> badgeList) {
        this.badgeList = badgeList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BadgeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.badge_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new BadgeRecyclerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(BadgeRecyclerViewHolder holder, int position) {
        BadgeCard curr = badgeList.get(position);
        int imageId = 0;
        int badgeId = curr.getBadgeId();
        switch(badgeId) {
            case 1:
                imageId = R.drawable.ic_baseline_star_24;
                break;
            case 2:
                imageId = R.drawable.emoji_2;
                break;
            case 3:
                imageId = R.drawable.emoji_3;
                break;
        }
        holder.badgeImg.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return badgeList == null ? 0 : badgeList.size();
    }
}
