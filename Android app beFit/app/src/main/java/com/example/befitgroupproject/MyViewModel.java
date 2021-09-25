package com.example.befitgroupproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    protected String name;
    protected int age;
    protected String gender;
    protected double Weight;
    protected double Height;
    protected String phone_number;
    protected List<FitnessTestResult> resultList;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return Weight;
    }

    public double getHeight() {
        return Height;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public List<FitnessTestResult> getResultList() {
        return resultList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setResultList(List<FitnessTestResult> resultList) {
        this.resultList = resultList;
    }
}
