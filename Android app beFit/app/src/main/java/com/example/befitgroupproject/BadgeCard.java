package com.example.befitgroupproject;

public class BadgeCard implements ItemClickListener {
    private String badgeName;
    private int badgeId;

    public BadgeCard(int badgeId) {
        this.badgeId = badgeId;
        this.badgeName = "badge_" + badgeId;;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getBadgeId() {
        return badgeId;
    }

    @Override
    public void onItemClick(int position) {
    }
}