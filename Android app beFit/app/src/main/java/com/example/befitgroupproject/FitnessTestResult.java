package com.example.befitgroupproject;

import java.util.Calendar;

public class FitnessTestResult {
    protected String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    ;
    protected String BMI;
    protected String coreStrength;
    protected String pushUp;
    protected String running;

    public FitnessTestResult() {

    }

    public FitnessTestResult(String date, String BMI, String coreStrength, String pushUp, String running) {
        this.date = date;
        this.BMI = BMI;
        this.coreStrength = coreStrength;
        this.pushUp = pushUp;
        this.running = running;
    }

    public String getDate() {
        return date;
    }

    public String getBMI() {
        return BMI;
    }

    public String getCoreStrength() {
        return coreStrength;
    }

    public String getPushUp() {
        return pushUp;
    }

    public String getRunning() {
        return running;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public void setCoreStrength(String coreStrength) {
        this.coreStrength = coreStrength;
    }

    public void setPushUp(String pushUp) {
        this.pushUp = pushUp;
    }

    public void setRunning(String running) {
        this.running = running;
    }
}


