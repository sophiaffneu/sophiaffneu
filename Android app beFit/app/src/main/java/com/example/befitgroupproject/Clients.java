package com.example.befitgroupproject;

public class Clients {
    protected userProfile userProfile;
    protected FitnessTestResult fitnessTestResult;


    public com.example.befitgroupproject.userProfile getUserProfile() {
        return userProfile;
    }

    public FitnessTestResult getFitnessTestResult() {
        return fitnessTestResult;
    }

    public void setUserProfile(com.example.befitgroupproject.userProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setFitnessTestResult(FitnessTestResult fitnessTestResult) {
        this.fitnessTestResult = fitnessTestResult;
    }
}
