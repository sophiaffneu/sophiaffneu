package com.example.befitgroupproject;

public class Plan_item {
    private int task_id;
    private String task_content;

    public Plan_item(int tId, String tCt) {
        this.task_id = tId;
        this.task_content = tCt;
    }

    public int getTaskId() {
        return task_id;
    }

    public String getTaskContent() {
        return task_content ;
    }
}
