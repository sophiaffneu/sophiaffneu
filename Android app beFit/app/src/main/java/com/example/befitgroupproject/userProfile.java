package com.example.befitgroupproject;

public class userProfile {
    protected String name;
    protected String dob;
    protected String gender;
    protected String Weight;
    protected String Height;
    protected String plan_selected;
    protected String phoneNumber;
    protected String email;
    protected String password;



    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getWeight() {
        return Weight;
    }

    public String getHeight() {
        return Height;
    }

    public String getPlan_selected() {
        return plan_selected;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setPlan_selected(String plan_selected) {
        this.plan_selected = plan_selected;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
