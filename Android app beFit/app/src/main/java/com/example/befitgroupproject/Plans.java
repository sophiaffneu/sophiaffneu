package com.example.befitgroupproject;

public class Plans {
    protected String description;
    protected String warmUp;
    protected String cardioTraining;
    protected String upperBody;
    protected String lowerBody;
    protected String coolDown;


    public Plans(){}

    public Plans(String description, String warnUp, String cardioTraining, String upperBody, String lowerBody, String coolDown) {
        this.description = description;
        this.warmUp = warnUp;
        this.cardioTraining = cardioTraining;
        this.upperBody = upperBody;
        this.lowerBody = lowerBody;
        this.coolDown = coolDown;
    }

    public String getDescription() {
        return description;
    }

    public String getWarmUp() {
        return warmUp;
    }

    public String getCardioTraining() {
        return cardioTraining;
    }

    public String getUpperBody() {
        return upperBody;
    }

    public String getLowerBody() {
        return lowerBody;
    }

    public String getCoolDown() {
        return coolDown;
    }

    public void setWarmUp(String warmUp) {
        this.warmUp = warmUp;
    }

    public void setCardioTraining(String cardioTraining) {
        this.cardioTraining = cardioTraining;
    }

    public void setUpperBody(String upperBody) {
        this.upperBody = upperBody;
    }

    public void setLowerBody(String lowerBody) {
        this.lowerBody = lowerBody;
    }

    public void setCoolDown(String coolDown) {
        this.coolDown = coolDown;
    }

    public void setDescription(String description) {
        this.description= description;
    }
}

